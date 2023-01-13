import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NonogramExcelExporter {
    public static void exportNonogram(ArrayList<Integer>[] nonogramArray, int columns,String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Nonogram");
        Row headerRow= sheet.createRow(0);
            // Print the column data
            for (int i = columns; i < nonogramArray.length; i++) {
                Cell cell = headerRow.createCell(i+1-columns);
                StringBuilder column = new StringBuilder();
                column.append("Column " + (i + 1 - columns) + ": ");
                for (int j = 0; j < nonogramArray[i].size(); j++) {
                    column.append(nonogramArray[i].get(j) + " ");
                }
                cell.setCellValue(column.toString());
            }

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Error saving Excel file.");
        }
    }
}



