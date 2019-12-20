package unicodeTech.TestSuiteC;

import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import unicodeTech.utility.MyMethods;

public class SearchProduct extends ChildTestSuiteC {

	ExtentTest ext;

	
	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsc, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}
		
		report.attachReporter(reporter);
		ext = report.createTest("SearchProduct");

	}
	
	@Test(dataProvider="getTestDataFromXLS")
	public void testSearchProduct(String keyword) {
		
		
		driver.get(sitedata.getProperty("url"));
		ext.log(Status.INFO, "URL Launched");
		isElementPresent("ip_keywords_name").sendKeys(keyword);
		isElementPresent("ip_keywords_name").sendKeys(Keys.ENTER);
		ext.log(Status.INFO, "Search Test with keyword"+"-"+keyword);
		report.flush();
	}
	
	@DataProvider
	public Object[][] getTestDataFromXLS() {
		
		return MyMethods.getTestData(tsc, "SearchProduct");
	}
	
	
}
