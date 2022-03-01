package steps
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DecimalFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable
import io.appium.java_client.functions.ExpectedCondition



class Page_Dashboard {

	@When("User clicks on Setting icon")
	def User_clicks_on_Setting_icon() {
		WebUI.waitForElementClickable(findTestObject('Object Repository/TinyPulse_Dashboards/span_Settings'), GlobalVariable.LONG_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/TinyPulse_Dashboards/span_Settings'))
	}

	@When("User clicks on Add People")
	def User_clicks_on_Add_People() {
		WebUI.waitForElementVisible(findTestObject('Object Repository/TinyPulse_Dashboards/span_AddPeople'), GlobalVariable.SHORT_TIMEOUT)
		WebUI.click(findTestObject('Object Repository/TinyPulse_Dashboards/span_AddPeople'))
	}

	@When("Add some users into list")
	def Add_some_users_into_list() {

		WebUI.setText(findTestObject('Object Repository/AddPeople/span_FirstName'), GlobalVariable.firstName1)
		WebUI.setText(findTestObject('Object Repository/AddPeople/span_LastName'), GlobalVariable.lastname1)
		WebUI.setText(findTestObject('AddPeople/input_email'), GlobalVariable.email1)
		WebUI.click(findTestObject('AddPeople/input_startDate'))
		WebUI.click(findTestObject('AddPeople/div_selectDataPicker1'))
		WebUI.click(findTestObject('AddPeople/input_manager'))
		WebUI.setText(findTestObject('AddPeople/input_SearchManager'), GlobalVariable.manager)
		WebUI.waitForElementClickable(findTestObject('AddPeople/div_selectEmailItem'), GlobalVariable.LONG_TIMEOUT)
		WebUI.click(findTestObject('AddPeople/div_selectEmailItem'))

		WebUI.setText(findTestObject('Object Repository/AddPeople/span_FirstName2'), GlobalVariable.firstName2)
		WebUI.setText(findTestObject('Object Repository/AddPeople/span_LastName2'), GlobalVariable.lastName2)
		WebUI.setText(findTestObject('AddPeople/input_email2'), GlobalVariable.email2)
		WebUI.click(findTestObject('AddPeople/input_startDate2'))
		WebUI.click(findTestObject('AddPeople/div_selectDataPicker2'))
		WebUI.click(findTestObject('AddPeople/input_manager'))
		WebUI.setText(findTestObject('AddPeople/input_SearchManager'), GlobalVariable.manager)
		WebUI.waitForElementClickable(findTestObject('AddPeople/div_selectEmailItem'), GlobalVariable.LONG_TIMEOUT)
		WebUI.click(findTestObject('AddPeople/div_selectEmailItem'))

		WebUI.click(findTestObject('AddPeople/btn_AddPeople'))
	}

	@Then("The Configurations page displays")
	def The_Configurations_page_displays() {
		WebUI.verifyElementVisible(findTestObject('Object Repository/AddPeople/div_Congratulations'))
		WebUI.closeBrowser()
	}
}
