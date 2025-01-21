package runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {

	public Map<String, String> loadClassMethods() {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/projectJSONs/classmethods.json";
		Map<String, String> classMethodMap = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		try {
			JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(filePath)));
			JSONArray classDetails = (JSONArray) classJSON.get("classdetails");
			for (int i = 0; i < classDetails.size(); i++) {
				JSONObject classDetail = (JSONObject) classDetails.get(i);
				String className = (String) classDetail.get("class");
				JSONArray methods = (JSONArray) classDetail.get("methods");
				for (int j = 0; j < methods.size(); j++) {
					String methodName = (String) methods.get(j);
					classMethodMap.put(methodName, className);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classMethodMap;
	}

	public int getTestDataSets(String filePath, String dataFlag) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(filePath)));
			JSONArray testDataSet = (JSONArray) classJSON.get("testData");
			for (int i = 0; i < testDataSet.size(); i++) {
				JSONObject testData = (JSONObject) testDataSet.get(i);
				String flag = (String) testData.get("flag");
				if (flag.equalsIgnoreCase(dataFlag)) {
					JSONArray data = (JSONArray) testData.get("data");
					return data.size();
				}
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public JSONObject getTestData(String filePath, String dataFlag, int iterationNumber) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject classJSON = (JSONObject) parser.parse(new FileReader(new File(filePath)));
			JSONArray testDataSet = (JSONArray) classJSON.get("testData");
			for (int i = 0; i < testDataSet.size(); i++) {
				JSONObject testData = (JSONObject) testDataSet.get(i);
				String flag = (String) testData.get("flag");
				if (flag.equalsIgnoreCase(dataFlag)) {
					JSONArray data = (JSONArray) testData.get("data");
					return (JSONObject) data.get(iterationNumber);
				}
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
