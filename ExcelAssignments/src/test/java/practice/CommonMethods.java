package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods implements ObjectRepository {

	ExtentReports extent;
	ExtentTest test;

	public WebDriver getDriver() {

		System.setProperty("webdriver.chrome.driver","C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}


	public ExtentTest startTestCase(String reportPath, String strTestCaseName) {
		extent = new ExtentReports(reportPath,true);
		test = extent.startTest(strTestCaseName);
		return test;
	}

	public void endTestCase() {
		extent.endTest(test);
		extent.flush();
	}

	public XSSFSheet readExcelData(String filePath) {

		FileInputStream fs = null;
		XSSFWorkbook wb = null;
		try {
			fs = new FileInputStream(filePath);	
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fs);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheetAt(0);

		return sheet;


	}

	public void invokeApplication(WebDriver driver, String url) {
		driver.get(url);
	}

	public void waitForAnElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String takeScreenshots(WebDriver driver,String screenshotName) {
		TakesScreenshot scrShot =((TakesScreenshot)driver);

		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+".png";
		File desFile = new File(destinationFile);
		try {
			FileUtils.copyFile(sourceFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationFile;
	}

	public String readPropertyFile(String propFilePath,String strPropertyValueToRead) {
		FileReader read = null;

		try {
			read = new FileReader(propFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(read);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value  = prop.getProperty(strPropertyValueToRead);
		return value;
	}

	public void selectFromDropdown(WebDriver driver, By dropDownLocaton, Object valueToBeSelected) {
		
		Select select = new Select(driver.findElement(dropDownLocaton));
		
		select.selectByVisibleText((String)valueToBeSelected);
	}

	public Boolean validateErrorMessage(WebDriver driver,By fieldToBeCheckedLocator, By errorLocator, Object expectedErrMessage,String invalidValue) {
		Boolean blnErrorFlag = true;
		driver.findElement(fieldToBeCheckedLocator).sendKeys(invalidValue);
		if (driver.findElement(errorLocator).isDisplayed()) {
			if(!driver.findElement(errorLocator).getText().equalsIgnoreCase(expectedErrMessage.toString().trim())) {
				blnErrorFlag = false;
			}
		}
		else {
			blnErrorFlag = false;
		}
		return blnErrorFlag;
	}

	public Actions moveToElement(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator));
		return action;
	}

	public void setField(WebDriver driver,ExtentTest test,By fieldToSetValue,Object value) {
		
		objCommonMethods.moveToElement(driver, fieldToSetValue);
		String screenshotName = driver.findElement(fieldToSetValue).getAttribute("name");
		driver.findElement(fieldToSetValue).sendKeys(value.toString());
		
		String path = objCommonMethods.takeScreenshots(driver,screenshotName);
		test.log(LogStatus.INFO, "Field :"+screenshotName.toUpperCase()+" was set to "+value+test.addScreenCapture(path));
	}
	}

