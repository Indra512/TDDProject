package runner;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	
	public ExcelUtil(String filePath) {
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCellData(String sheetName, int colNumber, int rowNumber) {
		sheet = workbook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowNumber);
		if (row == null)
			return "";
		XSSFCell cell = row.getCell(colNumber);
		if (cell == null)
			return "";
		if (cell.getCellType() == CellType.STRING)
			return cell.getStringCellValue();
		else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
			return String.valueOf(cell.getNumericCellValue());
		else if (cell.getCellType() == CellType.BOOLEAN)
			return String.valueOf(cell.getBooleanCellValue());
		else
			return "";
	}
}
