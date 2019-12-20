package unicodeTech.TestSuiteB;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import unicodeTech.basePackage.BaseInit;
import unicodeTech.utility.MyMethods;

public class ChildTestSuiteB extends BaseInit{

	@BeforeSuite
	public void checkTestSuite() throws Exception {
		
		startUP();
		boolean output = MyMethods.checkTestSuiteExecution(ts, "TestSuite", "TestSuiteB");
	
		if(!output) {
			
			throw new SkipException("Execution mode of the test suite TestSuiteB is set to NO");
		}
	
	}
}
