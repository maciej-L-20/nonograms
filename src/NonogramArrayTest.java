import java.util.ArrayList;

public class NonogramArrayTest {
    public NonogramArrayTest(ArrayList<Integer>[] data, int width) {
        // Print the row data
        System.out.println("Row data:");
        for (int i = 0; i < width; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }

        // Print the column data
        System.out.println("\nColumn data:");
        for (int i = width; i < data.length; i++) {
            System.out.print("Column " + (i - width) + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}

