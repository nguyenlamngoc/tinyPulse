package common
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.AssumptionViolatedException

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.Scenario
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import internal.GlobalVariable


class Login {
	
	@Given ("User logs in to TinyPulse")
	def User_logs_in_to_TinyPulse() {
		WebUI.openBrowser(GlobalVariable.BASE_URL)
		WebUI.maximizeWindow()
		//WebUI.navigateToUrl(GlobalVariable.BASE_URL)
		System.out.println(GlobalVariable.BASE_URL)

		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/login_Link'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/Page_Login/login_Link'))

		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/input_EnterUserName'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.sendKeys(findTestObject('Object Repository/Page_Login/input_EnterUserName'), GlobalVariable.USERNAME)

		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/button_Continue'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/Page_Login/button_Continue'))

		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/input_EnterPassword'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login/input_EnterPassword'), GlobalVariable.PASSWORD)

		WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Login/button_SignIn'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/Page_Login/button_SignIn'))

		WebUI.waitForElementVisible(findTestObject('Object Repository/TinyPulse_Dashboards/div_Dashboards'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.verifyElementVisible(findTestObject('Object Repository/TinyPulse_Dashboards/div_Dashboards'))
	}
}
