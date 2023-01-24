import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NonogramExcelExporter {
    private ArrayList<Integer>[] nonogramArray;
    private int columns;
    private String fileName;
    private String path;

    NonogramExcelExporter(ArrayList<Integer>[] nonogramArray, int columns, String fileName){
        this.nonogramArray = nonogramArray;
        this.columns = columns;
        this.fileName = fileName;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Nonogram");
        CellStyle columnStyle = workbook.createCellStyle();
        CellStyle rowStyle = workbook.createCellStyle();
        CellStyle emptyStyle = workbook.createCellStyle();
        writeColumnData(this.nonogramArray, this.columns, sheet,columnStyle);
        writeRowData(this.nonogramArray, this.columns, sheet, rowStyle, emptyStyle);
        sheet.autoSizeColumn(1);
        for (int i = 2; i <columns+30 ; i++) {
            sheet.setColumnWidth(i,3 * 256);
        }
        this.path=saveFile(this.fileName, workbook);
        System.out.println("Your nonogram is saved as Excel file to path: "+this.getPath());
    }

    public String getPath() {
        return path;
    }

    private String saveFile(String fileName, Workbook workbook){
        File myFile = new File(fileName + ".xlsx");
        try (FileOutputStream outputStream = new FileOutputStream(myFile)) {
            workbook.write(outputStream);
        } catch(IOException e) {
            System.out.println("Error saving Excel file.");
        }
        String absolutePath = myFile.getAbsolutePath();

        return absolutePath;
    }
    public void writeColumnData(ArrayList<Integer>[] nonogramArray, int columns, Sheet sheet, CellStyle style) {
        Row headerRow = sheet.createRow(0);
        Row dataRow = sheet.createRow(1);
        for (int i = columns; i < nonogramArray.length; i++) {
            Cell headerRowCell = headerRow.createCell(i + 2 - columns);
            Cell dataCell = dataRow.createCell(i + 2 - columns);
            String columnHeader = String.valueOf((i + 1 - columns));
            StringBuilder columnData = new StringBuilder();
            for (int j = nonogramArray[i].size(); j > 0; j--) {
                columnData.append(nonogramArray[i].get(j-1) + ", ");
            }
            if (columnData.length() > 0 && columnData.charAt(columnData.length() - 2) == ',') {
                columnData.deleteCharAt(columnData.length() - 2);
            }

            //Styling cells - black thin borders, counterclockwise rotation
            style.setRotation((short) 60);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);

            headerRowCell.setCellValue(columnHeader);
            dataCell.setCellValue(columnData.toString());
            dataCell.setCellStyle(style);
        }
    }
    public void writeRowData(ArrayList<Integer>[] nonogramArray, int columns, Sheet sheet, CellStyle style, CellStyle emptyStyle){
        for (int i = 0; i < columns; i++) {
            Row newRow = sheet.createRow(i + 2);
            Cell headerCell = newRow.createCell(0);
            Cell dataCell = newRow.createCell(1);
            for (int n = 0; n < nonogramArray.length - columns; n++) {
                Cell emptyCell = newRow.createCell(n + 2);

                emptyStyle.setBorderBottom(BorderStyle.THIN);
                emptyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                emptyStyle.setBorderLeft(BorderStyle.THIN);
                emptyStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                emptyStyle.setBorderRight(BorderStyle.THIN);
                emptyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
                emptyStyle.setBorderTop(BorderStyle.THIN);
                emptyStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

                emptyCell.setCellStyle(emptyStyle);
            }
            String rowHeader = String.valueOf((i + 1));
            StringBuilder rowData = new StringBuilder();
            for (int j = 0; j < nonogramArray[i].size(); j++) {
                rowData.append(nonogramArray[i].get(j) + ", ");
            }
            if (rowData.length() > 0 && rowData.charAt(rowData.length() - 2) == ',') {
                rowData.deleteCharAt(rowData.length() - 2);
            }

            //Styling cells - no rotation
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.RIGHT);


            headerCell.setCellValue(rowHeader);
            dataCell.setCellValue(rowData.toString());
            dataCell.setCellStyle(style);
        }
    }

}




