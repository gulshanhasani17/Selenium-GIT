package practice;



import org.openqa.selenium.WebDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CreateHTMLReports implements ObjectRepository  {

	static ExtentReports extent;
	static ExtentTest test;
	String reportPath = "C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\reports.html";
	String strTestCaseName = "CreateHTMLReports";
	static WebDriver driver;



	public static void main(String[] args) {

		test = objCommonMethods.startTestCase(objCreateHtmlReport.reportPath, objCreateHtmlReport.strTestCaseName);

		driver = objCommonMethods.getDriver();

		driver.get("http://www.google.com");
		if (driver.getTitle().equalsIgnoreCase("Gmail")) {
			test.log(LogStatus.PASS,"Browser opened successfully");
		}
		else {
			test.log(LogStatus.FAIL,"Correct browser did not opened");
		}

		driver.quit();
		test.log(LogStatus.PASS,"Browser closed");

		objCommonMethods.endTestCase();


	}
}
