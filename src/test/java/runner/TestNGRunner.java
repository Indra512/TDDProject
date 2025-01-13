package runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class TestNGRunner {

	/**
	 * TestNG -- Object of complete TestNG
	 * XmlSuite -- Single test suite
	 * List<XmlSuite> -- List of all test suites
	 * XmlTest -- Test within suite
	 * List<XmlTest> -- All tests under single suite
	 * Map<String, String> -- Test parameters 
	 * XmlClass -- single test class
	 * List<XmlClass> -- All test classes with single test
	 */
	TestNG testNG;
	XmlSuite suite;
	List<XmlSuite> allSuites;
	XmlTest test;
	List<XmlTest> allTests;
	Map<String, String> testParameters;
	XmlClass testClass;
	List<XmlClass> testClasses;
	
	public TestNGRunner(int suiteThreadPoolSize) {
		testNG = new TestNG();
		allSuites = new ArrayList<XmlSuite>();
		testNG.setSuiteThreadPoolSize(suiteThreadPoolSize);
		testNG.setXmlSuites(allSuites);
	}
	
	public void createSuite(String suiteName, boolean parallelTests) {
		suite = new XmlSuite();
		suite.setName(suiteName);
		if (parallelTests) {
			suite.setParallel(ParallelMode.TESTS);
		}
		allSuites.add(suite);
	}
	
	public void addListener(String listenerFile) {
		suite.addListener(listenerFile);
	}
	
	public void addTest(String testName) {
		test = new XmlTest(suite);
		test.setName(testName);
		
		testParameters = new HashMap<String, String>();
		testClasses = new ArrayList<XmlClass>();
		
		test.setParameters(testParameters);	
		test.setClasses(testClasses);
	}
	
	public void adddTestParameter(String name, String value) {
		testParameters.put(name, value);
	}
	
	public void addTestClass(String className, List<String> includeMethodNames) {
		testClass = new XmlClass(className);
		int priority = 1;
		List<XmlInclude> classMethods = new ArrayList<XmlInclude>();
		for (String methodName : includeMethodNames) {
			XmlInclude method = new XmlInclude(methodName, priority);
			classMethods.add(method);
			priority++;
		}
		testClass.setIncludedMethods(classMethods);
		testClasses.add(testClass);
	}
	public void run() {
		testNG.run();
	}
}
