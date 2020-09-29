package practice;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Assignment4 implements ObjectRepository {

	By popup_SeleniumEasy = By.xpath("//a[@class='at-cv-button at-cv-lightbox-yesno at-cm-no-button' and contains(text(),'No, thanks')]");
	By text_FirstName = By.xpath("//input[@name='first_name']");
	By text_LastName = By.xpath("//input[@name='last_name']");
	By text_Email = By.xpath("//input[@name='email']");
	By text_Phone = By.xpath("//input[@name='phone']");
	By error_ValidEmail = By.xpath("//small[@class='help-block' and contains(text(),'Please supply a valid email address')]");
	By error_ValidPhoneNumber = By.xpath("//small[@class='help-block' and contains(text(),'Please supply a vaild phone number with area code')]");
	By text_Address = By.xpath("//input[@name='address']");
	By text_City = By.xpath("//input[@name='city']");
	By text_ZipCode = By.xpath("//input[@name='zip']");
	By text_Domain = By.xpath("//input[@name='website']");
	By radio_HostingYes = By.xpath("//input[@name='hosting' and @type='radio' and @value='yes']");
	By radio_HostingNo = By.xpath("//input[@name='hosting' and @type='radio' and @value='no']");
	By text_ProjectDescription = By.xpath("//textarea[@name='comment']");
	By header_ContactUs = By.xpath("//legend[contains(text(),'Contact Us Today')]");
	By dropDown_InputForms = By.xpath("//a[@class='dropdown-toggle' and contains(text(),'Input Forms')]");
	By dropDownOpton_InputFormSubmit = By.xpath("//a[@class='dropdown-toggle' and contains(text(),'Input Forms')]/following-sibling::ul //a[contains(text(),'Input Form Submit')]");
	By button_Send = By.xpath("//button[@type='submit' and contains(text(),'Send')]");
	By select_State = By.xpath("//select[@name='state']");
	String url = "http://www.seleniumeasy.com/test/";
	String reportPath = "C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\AssignmentReport.html";
	String strTestCaseName = this.getClass().getName();

	Map<String,Object> testData = new HashMap<String,Object>();
	
	@Test
	public static void main() {
		
			
			//Create HTML report for the test
			ExtentTest test = objCommonMethods.startTestCase(objAssignment4.reportPath, objAssignment4.strTestCaseName);

			// Get the webdriver
			WebDriver driver = objCommonMethods.getDriver();
			driver.manage().window().maximize();

			// Reading url from properties file
			objAssignment4.url = objCommonMethods.readPropertyFile("C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\Property.properties","url");


			//Invoke the application at the given URL
			objCommonMethods.invokeApplication(driver,objAssignment4.url);
			String path = objCommonMethods.takeScreenshots(driver, "ApplicationInvoked");
			test.log(LogStatus.PASS,"Application Invoked succesfully"+test.addScreenCapture(path));

			// Find and close the initial information popup coming in the application 
			objCommonMethods.waitForAnElement(driver,objAssignment4.popup_SeleniumEasy);
			driver.findElement(objAssignment4.popup_SeleniumEasy).click();
			String pathIntitialPopup = objCommonMethods.takeScreenshots(driver,"PopupClosed");
			test.log(LogStatus.PASS,"Initial popup was closed successfully"+test.addScreenCapture(pathIntitialPopup));

			// Navigate to Input form Submit page and validate the availability of contact us text
			driver.findElement(objAssignment4.dropDown_InputForms).click();
			driver.findElement(objAssignment4.dropDownOpton_InputFormSubmit).click();
			objCommonMethods.waitForAnElement(driver,objAssignment4.header_ContactUs);
			String pathContactUs = objCommonMethods.takeScreenshots(driver,"InputFormSubmittedPage");
			test.log(LogStatus.PASS,"User was redirected to correct page : "+driver.getTitle().toUpperCase()+test.addScreenCapture(pathContactUs));


			// Load the data to be fed in the form from excel sheet
			XSSFSheet sheet = objCommonMethods.readExcelData("C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\Assignment_TestData.xlsx");
			for (int col = 0;col<=sheet.getRow(0).getLastCellNum()-1;col++) {
				Object value;
				String key = sheet.getRow(0).getCell(col).toString();
				if (key.toString().equalsIgnoreCase("Phone")||key.toString().equalsIgnoreCase("Zipcode")) {
					 value = sheet.getRow(1).getCell(col).getRawValue();
					 
				}
				else {
					 value = sheet.getRow(1).getCell(col).toString();
				}
				objAssignment4.testData.put(key,value);
			}
/*
			// Verifying error message in case of invalid email entered
			Boolean blnEmailErrorFlag = objCommonMethods.validateErrorMessage(driver,objAssignment4.text_Email,objAssignment4.error_ValidEmail,objAssignment4.testData.get("error_Email"),"gulshan");
			if(blnEmailErrorFlag) {
				String pathErr = objCommonMethods.takeScreenshots(driver,"CorrectErrorMessage");
				test.log(LogStatus.PASS,"Correct error message shown :"+driver.findElement(objAssignment4.error_ValidEmail).getText().toUpperCase()+test.addScreenCapture(pathErr));
			}
			else
			{
				String pathErr = objCommonMethods.takeScreenshots(driver,"IncorrectErrorMessage");
				test.log(LogStatus.FAIL,"Incorrect Error message is displayed :"+driver.findElement(objAssignment4.error_ValidEmail).getText().toUpperCase()+test.addScreenCapture(pathErr));
			}
			


			//Verifying error message in case of invalid phone number 
			Boolean blnPhoneErrorFlag = objCommonMethods.validateErrorMessage(driver,objAssignment4.text_Phone,objAssignment4.error_ValidPhoneNumber,objAssignment4.testData.get("error_Phone"),"99");
			if(blnPhoneErrorFlag) {
				String pathErr = objCommonMethods.takeScreenshots(driver,"CorrectErrorMessage");
				test.log(LogStatus.PASS,"Correct error message shown :"+driver.findElement(objAssignment4.error_ValidPhoneNumber).getText().toUpperCase()+test.addScreenCapture(pathErr));
			}
			else
			{
				String pathErr = objCommonMethods.takeScreenshots(driver,"IncorrectErrorMessage");
				test.log(LogStatus.FAIL,"Incorrect Error message is displayed :"+driver.findElement(objAssignment4.error_ValidPhoneNumber).getText().toUpperCase()+test.addScreenCapture(pathErr));
			}

*/
			//Fill all the fields the Input Submit form
			objCommonMethods.setField(driver, test, objAssignment4.text_FirstName,objAssignment4.testData.get("FirstName"));
			objCommonMethods.setField(driver, test, objAssignment4.text_LastName,objAssignment4.testData.get("LastName"));
			objCommonMethods.setField(driver, test, objAssignment4.text_Email,objAssignment4.testData.get("Email"));
			objCommonMethods.setField(driver, test, objAssignment4.text_Phone,objAssignment4.testData.get("Phone"));
			objCommonMethods.setField(driver, test, objAssignment4.text_Address,objAssignment4.testData.get("Address"));
			objCommonMethods.setField(driver, test, objAssignment4.text_City,objAssignment4.testData.get("City"));
			objCommonMethods.setField(driver, test, objAssignment4.text_ZipCode,objAssignment4.testData.get("Zipcode"));
			objCommonMethods.setField(driver, test, objAssignment4.text_Domain,objAssignment4.testData.get("Domain"));
			objCommonMethods.setField(driver, test, objAssignment4.text_ProjectDescription,objAssignment4.testData.get("ProjectDescription"));

			// Using Select class, selecting the value of the dropdown
			objCommonMethods.selectFromDropdown(driver,objAssignment4.select_State,objAssignment4.testData.get("State"));
			String pathStateSelected = objCommonMethods.takeScreenshots(driver,"StateSelected");
			test.log(LogStatus.PASS,"State was selected as :"+objAssignment4.testData.get("State").toString().toUpperCase()+test.addScreenCapture(pathStateSelected));
			
			// Select Radio button for Hosting
			driver.findElement(objAssignment4.radio_HostingYes).click();
			String pathHostingYes = objCommonMethods.takeScreenshots(driver,"HostingSetToYes");
			test.log(LogStatus.PASS,"Hosting was set to : YES"+test.addScreenCapture(pathHostingYes));

			//Click on the submit button
			driver.findElement(objAssignment4.button_Send).click();
			String pathSendButtonClicked = objCommonMethods.takeScreenshots(driver,"SendButtonClicked");
			test.log(LogStatus.PASS,"Send Button was Clicked"+test.addScreenCapture(pathSendButtonClicked));
			driver.quit();
			test.log(LogStatus.PASS,"Browser was closed as expected");
			objCommonMethods.endTestCase();



		}

		
	}
	

