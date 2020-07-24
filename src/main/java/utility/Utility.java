package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {
	public static Object[][] getAmountSampleData() {

		Object[][] sample_data = new Object[13][5];

		FileInputStream fis;
		try {
			fis = new FileInputStream(new File("./dataprovider/data_amount.xlsx"));

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			int sheetCount = wb.getNumberOfSheets();
			System.out.println(sheetCount);
			XSSFSheet ws = wb.getSheetAt(0);
			Iterator<Row> row = ws.iterator();
			//System.out.println("RowCount"+row.hasNext());
			Row firstRow = row.next();
			int rowCount = 0;
			Object value ;
			Cell currentCell;
			String cellTextValue="";
			
			DataFormatter formatter = new DataFormatter(Locale.US);
			
			while (row.hasNext()) {
				Row curr_row = row.next();
				Iterator<Cell> cellValue = curr_row.cellIterator();
				int column_count = 0;
				while (cellValue.hasNext()) {
					currentCell = cellValue.next();
					
					//System.out.print(currentCell.getCellType());
					 switch (currentCell.getCellType()) {
					 case NUMERIC:
						 cellTextValue = NumberToTextConverter.toText(currentCell.getNumericCellValue());
						 break;
					 case FORMULA:
						 formatter.formatCellValue(currentCell);
						 break;
					 default:
						 cellTextValue = currentCell.getStringCellValue();
					 }
					 //System.out.println("sample_data["+rowCount+"]["+column_count+"]="+cellTextValue);
					sample_data[rowCount][column_count]=cellTextValue;
					column_count++;
				}
				rowCount++;
			}
			

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		}
		return sample_data;

	}
}
