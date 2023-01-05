import java.util.ArrayList;

public class NonogramArrayFromPixelArray {
    public static void main(String[] args) {
        new ImageProcessor("C:\\Users\\Fujitsu\\IdeaProjects\\apro1_22z_pro_4.4\\src\\image.png");
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
        new NonogramArrayTest(nonogramArray, pixelArray[1].length);
    }
}
