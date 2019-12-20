package unicodeTech.TestSuiteA;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import unicodeTech.utility.MyMethods;

public class ValidateUser extends ChildTestSuiteA {

	int counter=1;
	
	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsa, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}

	}

	@Test(dataProvider="getTestDataFromXLS")
	public void testValidateUser(String username, String password) {

		ext = report.createTest(this.getClass().getSimpleName());
		
		driver.get(sitedata.getProperty("url"));
		ext.log(Status.INFO, "URL Loaded");
		MyMethods.signIN(username,password);

		MyMethods.getScreenShot("UserLogin"+counter, driver);
		
		try {

			WebElement newProdLink = isElementPresent("txt_HomePage_newProducts_linkText");

			if (newProdLink.isDisplayed()) {

				System.out.println("User has logged in successfully");

				logs.info("User has logged in successfully");

				MyMethods.signOUT();
			}

		} catch (Exception e) {

			System.out.println("Invalid username or password");
			logs.info("Invalid username or password");

		}
		counter++;
		
		report.flush();
	}
	
	@DataProvider
	public Object[][] getTestDataFromXLS() {
		
		return MyMethods.getTestData(tsa, "ValidateUser");
	}
	
	
	
	
	
	
	
	
	
}
