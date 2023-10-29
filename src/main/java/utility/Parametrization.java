package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parametrization {
	
	public static String getStringValue(String Sheetname,int rowno,int cellno) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Mr. Sunil\\eclipse-workspace\\Naptol\\src\\test\\resources\\TestData.xlsx");
		String value=WorkbookFactory.create(file).getSheet(Sheetname).getRow(rowno).getCell(cellno).getStringCellValue();
		return value;
	}
	public static Double getDoubleValue(String Sheetname,int rowno,int cellno) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Mr. Sunil\\eclipse-workspace\\Naptol\\src\\test\\resources\\TestData.xlsx");
		Double value=WorkbookFactory.create(file).getSheet(Sheetname).getRow(rowno).getCell(cellno).getNumericCellValue();
		return value;
	}

}
