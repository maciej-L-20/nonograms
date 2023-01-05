import java.util.ArrayList;

public class NonogramArrayTest {
    public NonogramArrayTest(ArrayList<Integer>[] data, int columns) {
        // Print the row data
        System.out.println("Row data:");
        for (int i = 0; i < columns; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }

        // Print the column data
        System.out.println("\nColumn data:");
        for (int i = columns; i < data.length; i++) {
            System.out.print("Column " + (i - columns) + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}

