import java.util.ArrayList;

public class NonogramArrayFromPixelArray {
    NonogramArrayFromPixelArray(String path, int columns, int rows){
        new ImageProcessor(path, columns, rows);
        int[][] pixelArray = ImageProcessor.getPixelArray();

        // Create the array of ArrayLists
        ArrayList<Integer>[] nonogramArray = new ArrayList[pixelArray.length + pixelArray[1].length];
        for (int i = 0; i < nonogramArray.length; i++) {
            nonogramArray[i] = new ArrayList<>();
        }

        // Write the row data
        int index = 0;
        for (int y = 0; y < pixelArray[0].length; y++) {
            int count = 0;
            for (int x = 0; x < pixelArray.length; x++) {
                if (pixelArray[x][y] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        nonogramArray[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                nonogramArray[index].add(count);
            }
            index++;
        }

        // Write the column data
        for (int x = 0; x < pixelArray.length; x++) {
            int count = 0;
            for (int y = 0; y < pixelArray[0].length; y++) {
                if (pixelArray[x][y] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        nonogramArray[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                nonogramArray[index].add(count);
            }
            index++;
        }
    }
    //Constructor for choosing the level
    NonogramArrayFromPixelArray(String path, int level){
        ImageProcessor imageProcessor = new ImageProcessor(path, level);
        int[][] pixelArray = ImageProcessor.getPixelArray();

        // Create the array of ArrayLists
        ArrayList<Integer>[] nonogramArray = new ArrayList[pixelArray.length + pixelArray[1].length];
        for (int i = 0; i < nonogramArray.length; i++) {
            nonogramArray[i] = new ArrayList<>();
        }

        // Write the row data
        int index = 0;
        for (int y = 0; y < pixelArray[0].length; y++) {
            int count = 0;
            for (int x = 0; x < pixelArray.length; x++) {
                if (pixelArray[x][y] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        nonogramArray[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                nonogramArray[index].add(count);
            }
            index++;
        }

        // Write the column data
        for (int x = 0; x < pixelArray.length; x++) {
            int count = 0;
            for (int y = 0; y < pixelArray[0].length; y++) {
                if (pixelArray[x][y] == 1) {
                    count++;
                } else {
                    if (count > 0) {
                        nonogramArray[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                nonogramArray[index].add(count);
            }
            index++;
        }
        new NonogramArrayDisplay(nonogramArray, pixelArray[1].length);
        new NonogramExcelExporter(nonogramArray, pixelArray[1].length,"Nonogram");
    }
}
