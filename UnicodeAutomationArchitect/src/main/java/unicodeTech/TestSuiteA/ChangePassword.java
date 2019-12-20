package unicodeTech.TestSuiteA;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class ChangePassword extends ChildTestSuiteA {

	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsa, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}

	}
	
	@Test
	public void testChangePassword() {

		driver.get(sitedata.getProperty("url"));
		MyMethods.signIN("demo@unicodetechnologies.in", "unicode");

		isElementPresent("lnk_myaccount_linkText").click();
		isElementPresent("lnk_ChangePwd_linkText").click();
		isElementPresent("ip_currentPwd_name").sendKeys("unicode");
		isElementPresent("ip_newPwd_name").sendKeys("unicode12");
		isElementPresent("ip_confirmPwd_name").sendKeys("unicode12");
		isElementPresent("btn_continue_pwd_id").click();

		logs.info("Password has been changed successfully");

		MyMethods.getScreenShot("PwdChange", driver);
		
		MyMethods.signOUT();

		logs.info("Login with new Password");

		MyMethods.signIN("demo@unicodetechnologies.in", "unicode12");
		MyMethods.getScreenShot("LoginWthNewPWd", driver);

		isElementPresent("lnk_myaccount_linkText").click();
		isElementPresent("lnk_ChangePwd_linkText").click();
		isElementPresent("ip_currentPwd_name").sendKeys("unicode12");
		isElementPresent("ip_newPwd_name").sendKeys("unicode");
		isElementPresent("ip_confirmPwd_name").sendKeys("unicode");
		isElementPresent("btn_continue_pwd_id").click();

		logs.info("Password has been reset");

		MyMethods.signOUT();

	}
}
