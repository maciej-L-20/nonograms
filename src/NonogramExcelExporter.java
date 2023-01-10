import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NonogramExcelExporter {
    public static void exportNonogram(ArrayList<Integer>[] nonogramArray, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Nonogram");

        int numRows = nonogramArray[0].size();
        int numCols = nonogramArray.length - numRows;

        // Write the row headers
        for (int i = 0; i < numRows; i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue("Row " + (i + 1));
        }

        // Write the row data
        for (int i = 0; i < numRows; i++) {
            Row row = sheet.getRow(i);
            ArrayList<Integer> rowData = nonogramArray[i];
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j + 1);
                cell.setCellValue(rowData.get(j));
            }
        }

        // Write the column headers
        Row row = sheet.createRow(numRows * 2 + 1);
        for (int i = 0; i < numCols; i++) {
            Cell cell = row.createCell(i + 1);
            cell.setCellValue("Column " + (i + 1));
        }

        // Write the column data
        for (int i = numRows; i < nonogramArray.length; i++) {
            row = sheet.createRow(i - numRows + numRows * 2 + 2);
            ArrayList<Integer> colData = nonogramArray[i];
            for (int j = 0; j < colData.size(); j++) {
                Cell cell = row.createCell(j + 1);
                cell.setCellValue(colData.get(j));
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Error saving Excel file.");
            return;
        }
    }
}

