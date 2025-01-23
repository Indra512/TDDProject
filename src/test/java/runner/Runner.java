package runner;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Runner {

//	public static void main(String args[]) {
//		TestNGRunner testNG = new TestNGRunner(1);
//		testNG.createSuite("Manage Stocks Suite", false);
//		testNG.addListener("listener.MyTestListener");
//		testNG.addTest("Modify Stock : Sell Stock");
//		testNG.adddTestParameter("action", "buyStock");
//
//		// Add login test method
//		List<String> includeMethods = new ArrayList<String>();
//		includeMethods.add("login");
//		testNG.addTestClass("testCases.ManageSessionTest", includeMethods);
//
//		// Add select portfolio method
//		includeMethods = new ArrayList<String>();
//		includeMethods.add("selectPortfolio");
//		testNG.addTestClass("testCases.ManagePortfolioTest", includeMethods);
//
//		// Add modify stock methods
//		includeMethods = new ArrayList<String>();
//		includeMethods.add("verifyStockIsPresent");
//		includeMethods.add("modifyStock");
//		includeMethods.add("verifyStockIsPresent");
//		includeMethods.add("verifyStockQuantity");
//		includeMethods.add("verifyStockTransactionHistory");
//		testNG.addTestClass("testCases.ManageStocksTest", includeMethods);
//
//		testNG.run();
//	}
	
	
	public static void main(String[] args) {
		Map<String, String> classMethodMap = new JsonUtil().loadClassMethods();
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/testconfig.json";
		JSONParser parser = new JSONParser();
		try {
			JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(filePath)));
			String parallelsuites = (String) classJSON.get("parallelsuites");
			JSONArray testsuites = (JSONArray) classJSON.get("testsuites");
			TestNGRunner testNG = new TestNGRunner(Integer.parseInt(parallelsuites));
			for (int i = 0; i < testsuites.size(); i++) {
				JSONObject testSuite = (JSONObject) testsuites.get(i);
				String runMode = (String) testSuite.get("runmode");
				if (runMode.equalsIgnoreCase("Yes")) {
					String suiteName = (String) testSuite.get("name");
					String paralleltests = (String) testSuite.get("paralleltests");
					String suiteFileName = (String) testSuite.get("suitefilename");
					String testDataJsonFileName = (String) testSuite.get("testdatajsonfile");
//					String testDataExcelFileName = (String) testSuite.get("testdataxlsxfile");
					boolean pTest = false;
					if (paralleltests.equalsIgnoreCase("Yes")) {
						pTest = true;
					}
					testNG.createSuite(suiteName, pTest);
					testNG.addListener("listener.MyTestListener");
					String suiteFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/"
							+ suiteFileName;
					JSONParser suiteParser = new JSONParser();
					JSONObject suiteClassJSON = (JSONObject) suiteParser.parse(new FileReader(new File(suiteFilePath)));
					JSONArray testCases = (JSONArray) suiteClassJSON.get("testCases");
					for (int k = 0; k < testCases.size(); k++) {
						JSONObject testCase = (JSONObject) testCases.get(k);
						String testName = (String) testCase.get("name");
						JSONArray executions = (JSONArray) testCase.get("executions");
						for (int l = 0; l < executions.size(); l++) {
							JSONObject execution = (JSONObject) executions.get(l);
							String dataFlag = (String) execution.get("dataflag");
							
//							For JSON Data
							String testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + testDataJsonFileName;
							int testDataSets = new JsonUtil().getTestDataSets(testDataFilePath, dataFlag);
							
//							For Excel Data
//							String testDataFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/"+ testDataExcelFileName;
//							int testDataSets = new ExcelReader().getTestDataSets(testDataFilePath, suiteName, dataFlag);
							
							for (int x = 0; x < testDataSets; x++) {
								testNG.addTest(testName + " Method " + (x + 1));
								JSONArray parameterNames = (JSONArray) execution.get("parameternames");
								JSONArray parameterValues = (JSONArray) execution.get("parametervalues");

								if (parameterNames.size() != 0 && parameterValues.size() != 0) {
									for (int m = 0; m < parameterNames.size(); m++) {
										testNG.adddTestParameter(parameterNames.get(m).toString(),
												parameterValues.get(m).toString());
									}
								}
//								For JSON Data
								testNG.adddTestParameter("testDataFilePath", testDataFilePath);
								testNG.adddTestParameter("dataFlag", dataFlag);
								testNG.adddTestParameter("iterationNumber", String.valueOf(x));
								
//								For Excel Data
//								testNG.adddTestParameter("testDataFilePath", testDataFilePath);
//								testNG.adddTestParameter("dataFlag", dataFlag);
//								testNG.adddTestParameter("iterationNumber", String.valueOf(x));
//								testNG.adddTestParameter("sheetName", suiteName);

								JSONArray methods = (JSONArray) execution.get("methods");
								List<String> includeMethods = new ArrayList<String>();
								for (int n = 0; n < methods.size(); n++) {
									String method = (String) methods.get(n);
									String currentClassName = classMethodMap.get(method);
									if (n == methods.size() - 1
											|| !currentClassName.equals(classMethodMap.get(methods.get(n + 1)))) {
										includeMethods.add(method);
										testNG.addTestClass(currentClassName, includeMethods);
										includeMethods = new ArrayList<String>();
									} else {
										includeMethods.add(method);
									}

								}
							}
						}
					}
				}
			}
			testNG.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
