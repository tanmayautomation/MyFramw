package unicodeTech.basePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*1. Inititalize and load properties file
2. Launch browser
3. Define webdriver
4. Define logs
5. Maximize browser window
6. Define timeout
7. Delete all cookies
8. Initialize ExcelFileReader objects
9. Create isElementPresent method
10. Extent Report initialization
*/

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import unicodeTech.utility.ExcelFileReader;

public class BaseInit {

	public static WebDriver driver;
	public static Properties sitedata;
	public static Properties storage;
	public static Logger logs;
	public static ExcelFileReader ts;
	public static ExcelFileReader tsa;
	public static ExcelFileReader tsb;
	public static ExcelFileReader tsc;
	public static ExtentHtmlReporter reporter = new ExtentHtmlReporter(
			System.getProperty("user.dir")+"\\src\\main\\resources\\unicodeTech\\reports\\TestResults.html");
	public static ExtentReports report = new ExtentReports();
	public static ExtentTest ext;

	public void startUP() throws Exception {

		if (driver == null) {

			report.attachReporter(reporter);
			report.setSystemInfo("Tester", "Tanmay");
			report.setSystemInfo("Environment", "QA");

			ext = report.createTest("BaseInit Class");

			logs = Logger.getLogger("devpinoyLogger");

			logs.info("SiteData Properties File is loading");
			ext.log(Status.INFO, "SiteData Properties File is loading");
			sitedata = new Properties();
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//propertiesData//SiteData.properties");
			sitedata.load(fi);

			logs.info("SiteData Properties File loaded");
			ext.log(Status.INFO, "SiteData Properties File loaded");

			logs.info("ObjectStorage Properties File is loading");
			ext.log(Status.INFO, "ObjectStorage Properties File is loading");

			storage = new Properties();
			fi = new FileInputStream(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//propertiesData//ObjectStorage.properties");
			storage.load(fi);
			logs.info("ObjectStorage Properties File loaded");
			ext.log(Status.INFO, "ObjectStorage Properties File loaded");

			String browserVal = sitedata.getProperty("browser");

			if (browserVal.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						"D:\\Software\\Testing-Tools\\Selenium\\WebDriver\\Drivers\\IEChromeFirefox\\19092018\\geckodriver.exe");
				driver = new FirefoxDriver();
				ext.log(Status.INFO, "Firefox browser launched");

			} else if (browserVal.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						"D:\\Software\\Testing-Tools\\Selenium\\WebDriver\\Drivers\\IEChromeFirefox\\19092018\\chromedriver.exe");
				driver = new ChromeDriver();
				ext.log(Status.INFO, "Chrome browser launched");

			} else if (browserVal.equalsIgnoreCase("ie")) {

				System.setProperty("webdriver.ie.driver",
						"D:\\Software\\Testing-Tools\\Selenium\\WebDriver\\Drivers\\IEChromeFirefox\\19092018\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				ext.log(Status.INFO, "IE browser launched");

			} else {

				System.out.println("No browser defined");
				ext.log(Status.INFO, "No browser defined");

			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			ext.log(Status.INFO, "Timeout defined and cookied deleted");

			ts = new ExcelFileReader(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//testInformation//TestSuite.xlsx");
			tsa = new ExcelFileReader(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//testInformation//TestSuiteA.xlsx");
			tsb = new ExcelFileReader(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//testInformation//TestSuiteB.xlsx");
			tsc = new ExcelFileReader(System.getProperty("user.dir")
					+ "//src//main//resources//unicodeTech//testInformation//TestSuiteC.xlsx");
			ext.log(Status.INFO, "ExcelFileReader objects created");

			report.flush();
		}
	}

	public static WebElement isElementPresent(String propKey) {

		try {

			if (propKey.contains("xpath")) {

				return driver.findElement(By.xpath(storage.getProperty(propKey)));

			} else if (propKey.contains("id")) {

				return driver.findElement(By.id(storage.getProperty(propKey)));

			} else if (propKey.contains("name")) {

				return driver.findElement(By.name(storage.getProperty(propKey)));

			} else if (propKey.contains("linkText")) {

				return driver.findElement(By.linkText(storage.getProperty(propKey)));

			} else {

				System.out.println("Key not found in the properties file");
				return null;

			}

		} catch (Exception e) {

		}
		return null;
	}

}
