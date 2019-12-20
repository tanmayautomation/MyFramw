package unicodeTech.TestSuiteB;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class NewProductTable extends ChildTestSuiteB{

	
	@BeforeTest
	public void checkTestCase() {

		boolean output = MyMethods.checkTestCaseExecution(tsb, "TestCase", this.getClass().getSimpleName());

		if (!output) {

			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}

	}
	
	@Test
	public void testNewProductTable() {
		
		driver.get(sitedata.getProperty("url"));
		
		WebElement tblNewProduct = isElementPresent("tbl_newProducts_xpath");
	
		List<WebElement> tableRows = tblNewProduct.findElements(By.tagName("tr"));
	
		for(int row=0;row<tableRows.size();row++) {
			
			List<WebElement> tableCols = tableRows.get(row).findElements(By.tagName("td"));
			
			for(int col=0;col<tableCols.size();col++) {
				
				System.out.print(tableCols.get(col).getText()+"      ");
			}
			
			System.out.println();
			
		}
		
	}
}
