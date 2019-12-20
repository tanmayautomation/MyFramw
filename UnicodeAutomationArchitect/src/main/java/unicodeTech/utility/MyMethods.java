package unicodeTech.utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import unicodeTech.basePackage.BaseInit;

public class MyMethods extends BaseInit{

	public static void signIN(String username, String password) {
		
		driver.get(sitedata.getProperty("url"));
		//driver.findElement(By.linkText(storage.getProperty("lnk_logurselfIn_linkText"))).click();
		
		isElementPresent("lnk_logurselfIn_linkText").click();
		isElementPresent("ip_email_name").sendKeys(username);
		isElementPresent("ip_password_name").sendKeys(password);
		isElementPresent("btn_signin_id").click();

	}
	
	public static void signOUT() {
		
		isElementPresent("lnk_logOff_linkText").click();
		isElementPresent("btn_continue_id").click();

		
		checkTestSuiteExecution(ts,"TestSuite","TestSuiteC");
	}
	
	public static boolean checkTestSuiteExecution(ExcelFileReader data, String sheetName, String testsuiteID) {
		
		
		
		int rows = data.totalRow(sheetName);
		
		for(int row=1;row<rows;row++) {
			
			String suiteName = data.getData(sheetName, row, 0);
			
			if(suiteName.equalsIgnoreCase(testsuiteID)) {
				
				String exeMode = data.getData(sheetName, row, 2);
				
				if(exeMode.equalsIgnoreCase("Y"))
					
					return true;
				else
					return false;
				
				
			}
			
		}
		return false;

	}
	
	
public static boolean checkTestCaseExecution(ExcelFileReader data, String sheetName, String testcaseID) {
		
		
		
		int rows = data.totalRow(sheetName);
		
		for(int row=1;row<rows;row++) {
			
			String tcName = data.getData(sheetName, row, 0);
			
			if(tcName.equalsIgnoreCase(testcaseID)) {
				
				String exeMode = data.getData(sheetName, row, 2);
				
				if(exeMode.equalsIgnoreCase("Y"))
					
					return true;
				else
					return false;
				
				
			}
			
		}
		return false;

	}
	
	public static Object[][] getTestData(ExcelFileReader data, String sheetName) {
		
		int cols = data.totalColumn(sheetName);
		int rows = data.totalRow(sheetName);
		
		Object[][] myData = new Object[rows-1][cols];
		
		for(int row=1;row<rows;row++) {
			
			for(int col=0;col<cols;col++) {
				
				myData[row-1][col] = data.getData(sheetName, row, col);
			}
		}
		
		return myData;
	}
	
public static String getScreenShot(String imageName, WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\unicodeTech\\screenshots\\"+imageName+System.currentTimeMillis()+".png";
		
		//System.out.println(path);
		File destination =new File(path);
		
	    try {
	    	
	    	FileHandler.copy(scrFile,destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
	    
	    return path;
	}
	
	
}
