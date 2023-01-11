import java.util.ArrayList;

public class NonogramArrayDisplay {
    public NonogramArrayDisplay(ArrayList<Integer>[] data, int columns) {
        System.out.println("Enjoy your nonogram!");
        // Print the row data
        System.out.println("Row data:");
        for (int i = 0; i < columns; i++) {
            System.out.print("Row " + (i+1) + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }

        // Print the column data
        System.out.println("\nColumn data:");
        for (int i = columns; i < data.length; i++) {
            System.out.print("Column " + (i+1 - columns) + ": ");
            for (int j = 0; j < data[i].size(); j++) {
                System.out.print(data[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}

