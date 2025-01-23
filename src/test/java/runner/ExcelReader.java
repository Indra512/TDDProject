package runner;

import org.json.simple.JSONObject;

public class ExcelReader {
	
	public JSONObject getTestData(String filePath, String sheetName, String dataFlag, int iterationNumber) {
		ExcelUtil excelUtil = new ExcelUtil(filePath);
		int flagRowNumber = 0;
		int index = 0;
		while (!excelUtil.getCellData(sheetName, 0, flagRowNumber).equalsIgnoreCase(dataFlag)) {
			flagRowNumber++;
		}
		int colStartRowNumber = flagRowNumber + 1;
		int dataStartRowNumber = flagRowNumber + 2;

		while (!excelUtil.getCellData(sheetName, 0, dataStartRowNumber).equals("")) {
			int colNumber = 0;
			JSONObject json = new JSONObject();
			if (index == iterationNumber) {
				while (!excelUtil.getCellData(sheetName, colNumber, dataStartRowNumber).equals("")) {
					String colName = excelUtil.getCellData(sheetName, colNumber, colStartRowNumber);
					String data = excelUtil.getCellData(sheetName, colNumber, dataStartRowNumber);
					json.put(colName, data);
					colNumber++;
				}
				return json;
			} else {
				index++;
			}
			dataStartRowNumber++;
		}
		return null;
	}
	
	public int getTestDataSets(String filePath, String sheetName, String dataFlag) {
		ExcelUtil excelUtil = new ExcelUtil(filePath);
		int flagRowNumber = 0;
		while (!excelUtil.getCellData(sheetName, 0, flagRowNumber).equalsIgnoreCase(dataFlag)) {
			flagRowNumber++;
		}
		int dataStartRowNumber = flagRowNumber + 2;
		int totalRows = 0;

		while (!excelUtil.getCellData(sheetName, 0, dataStartRowNumber).equals("")) {
			totalRows++;
			dataStartRowNumber++;
		}
		return totalRows;
	}
}
