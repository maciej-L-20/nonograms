import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NonogramFromArray {
    public static void main(String[] args) {
        new ImageProcessor();
        int[][] pixelArray = ImageProcessor.getPixelArray();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nonogram.csv"))) {
            // Write the row and column headers
            writer.write(" , ");
            for (int i = 0; i < pixelArray.length; i++) {
                writer.write(i + ",");
            }
            writer.newLine();

            // Write the data
            int row = 0;
            for (int y = 0; y < pixelArray[0].length; y++) {
                int nulls = 0;
                StringBuilder sb = new StringBuilder();
                int count = 0;
                for (int x = 0; x < pixelArray.length; x++) {
                    if (pixelArray[x][y] == 1) {
                        count++;
                    } else {
                        if (count > 0) {
                            sb.append(count).append(",");
                            count = 0;
                            nulls++;
                        }
                        nulls++;
                    }
                }
                if (count > 0) {
                    sb.append(count).append(",");
                    nulls++;
                }
                for (int i = 0; i < 100 - nulls; i++) {
                    sb.append(",");
                }
                writer.write(row + "," + sb.toString());
                writer.newLine();
                row++;
            }

            // Write the column data
            for (int x = 0; x < pixelArray.length; x++) {
                int nulls = 0;
                StringBuilder sb = new StringBuilder();
                int count = 0;
                for (int y = 0; y < pixelArray[0].length; y++) {
                    if (pixelArray[x][y] == 1) {
                        count++;
                    } else {
                        if (count > 0) {
                            sb.append(count).append(",");
                            count = 0;
                            nulls++;
                        }
                        nulls++;
                    }
                }
                if (count > 0) {
                    sb.append(count).append(",");
                    nulls++;
                }
                for (int i = 0; i < 200 - nulls; i++) {
                    sb.append(",");
                }
                writer.write(x + "," + sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            return;
        }
    }
}
