package unicodeTech.TestSuiteC;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class HomePageLinks extends ChildTestSuiteC{

	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsc, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}

	}
	
	@Test
	public void testHomePageLinks() {
		
		driver.get(sitedata.getProperty("url"));

		
		List<WebElement> allHomePageLinks = driver.findElements(By.tagName("a"));
		
		for(int count = 0;count<allHomePageLinks.size();count++) {
			
			System.out.println(allHomePageLinks.get(count).getText());
			
			/*String pageTitle = driver.getTitle();
			
			if(!(pageTitle.isEmpty())) {
				
				System.out.println("Valid link");
			}else {
				
				System.out.println("Broken Link");
			}*/
			
		}
		
	}
}
