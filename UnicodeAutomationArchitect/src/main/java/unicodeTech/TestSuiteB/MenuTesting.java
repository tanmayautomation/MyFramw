package unicodeTech.TestSuiteB;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class MenuTesting extends ChildTestSuiteB{

	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsb, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}

	}
	
	@Test
	public void testMenuTesting() throws InterruptedException {
		
		driver.get(sitedata.getProperty("url"));
		WebElement mainMenu = isElementPresent("div_menu_xpath");
		
		List<WebElement> allMenu  = mainMenu.findElements(By.tagName("a"));
	
		for(int count=0;count<allMenu.size();count++) {
			
			allMenu.get(count).click();
			Thread.sleep(2000);
			
			driver.navigate().back();
			mainMenu = isElementPresent("div_menu_xpath");
			
			allMenu  = mainMenu.findElements(By.tagName("a"));
			
			
		}
		
		
		
	}
}
