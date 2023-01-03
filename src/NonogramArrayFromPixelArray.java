import java.util.ArrayList;

public class NonogramArrayFromPixelArray {
    public static void main(String[] args) {
        new ImageProcessor();
        int[][] pixelArray = ImageProcessor.getPixelArray();

        // Create the array of ArrayLists
        ArrayList<Integer>[] data = new ArrayList[pixelArray.length + pixelArray[1].length];
        for (int i = 0; i < data.length; i++) {
            data[i] = new ArrayList<>();
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
                        data[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                data[index].add(count);
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
                        data[index].add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                data[index].add(count);
            }
            index++;
        }
        new NonogramArrayTest(data, pixelArray[1].length);
    }
}
