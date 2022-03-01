
import java.nio.file.Paths
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern


import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.driver.DriverFactory


import groovy.time.TimeCategory
import internal.GlobalVariable as GlobalVariable

class TestListener {

	@BeforeTestSuite
	def createRuntimeGlobalVar() {
		List<String> listFiles = new ArrayList<>()

		long current = 0
		while (current < 10000) {
			def timeStart = new Date()
			File dir = new File(System.getProperty("user.dir"));

			for (File file : dir.listFiles()) {
				if (file.getName().matches("testrun.*.txt")) {
					listFiles.add(file.getName());
					break;
				}
			}
			if (listFiles.size() > 0) {
				break;
			}
			
			Thread.sleep(1000);
			long time = TimeCategory.minus(new Date(), timeStart).toMilliseconds();
			current += time;
		}

		if (listFiles.size() == 0) {
			final String testRunName = GlobalVariable.TEST_RUN_PREFIX_NAME + GlobalVariable.TEST_PLAN_ID + "_" + DateTimeFormatter.ISO_INSTANT.format(Instant.now());
			
			ApiObject.run = ApiObject.core.createTestRun(Integer.valueOf((String) GlobalVariable.TEST_PLAN_ID),
				testRunName, GlobalVariable.BUILD_DEFINITION_ID, GlobalVariable.RELEASE_DEFINITION_ID);
			
			int runId = ApiObject.run == null ? 0 : ApiObject.run.getId();
			File file = Paths.get(System.getProperty("user.dir"), "testrun_" + runId + ".txt").toFile();
			file.createNewFile();
		} else {
			Pattern p = Pattern.compile('[0-9]+');
			Matcher matcher = p.matcher(listFiles[0]);
			matcher.matches();
			matcher.find();

			try {
				ApiObject.run = ApiObject.webApi.getTestRunApi().getTestRun(Integer.parseInt(matcher.group()));
			} catch (Exception e) {
				// Do nothing
			}
		}
	}

	@AfterTestSuite
	def actionAfterTestSuite() {
		// Add failed mapping log of Scenarios from scripts as a Test Run attachment
		if (Utilities.scenariosList.size() > 0) {
			//TestAttachmentRequestModel attachment = new TestAttachmentRequestModel("GeneralAttachment",
			//	GlobalVariable.SCENARIO_FAILS_TO_MAP_WITH_TEST_CASE_FILE, Base64.getEncoder().encodeToString(Utilities.scenariosList.join("\n").getBytes()));
			
			ApiObject.webApi.getAttachmentApi().createTestRunAttachment(ApiObject.run.getId(), attachment);
		}
		
		// Add failed mapping log if Test Cases from Azure DevOps as a Test Run attachment
		if (Utilities.testCaseIdsList != null) {
			if (Utilities.testCaseIdsList.size() > 0) {
				for (int i = 0; i < Utilities.testCaseIdsList.size(); i++) {
					Utilities.testCaseIdsList.set(i, GlobalVariable.NO_SCENARIOS_MESSAGE.replace("{id}", Utilities.testCaseIdsList[i].toString()))
				}

				//TestAttachmentRequestModel attachment = new TestAttachmentRequestModel("GeneralAttachment",
				//	GlobalVariable.TEST_CASE_FAIL_TO_MAP_WITH_SCENARIO_FILE, Base64.getEncoder().encodeToString(Utilities.testCaseIdsList.join("\n").getBytes()));
				
				ApiObject.webApi.getAttachmentApi().createTestRunAttachment(ApiObject.run.getId(), attachment);
			}
		}
		
		// Change state of Test Run to Completed
		if (ApiObject.run == null) return;
		ApiObject.core.completeTestRun(ApiObject.run.getId());
	}
}