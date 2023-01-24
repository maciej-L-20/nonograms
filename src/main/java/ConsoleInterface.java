import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {
    public static ArrayList<String> pictures;
    ConsoleInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 1 for nonogram generator, 2 for nonogram solver, 3 for nonogram generator that tests the output");
        int temp = scanner.nextInt();
        if (temp == 1) {
            generator();
        } else if (temp == 2) {
            solver();
        } else if (temp == 3) {
            generatorWithSolver();
        } else {
            new ConsoleInterface();
        }
    }

    public static void main(String[] args) {
        pictures=new ArrayList<>();
        pictures.add("Dinosaur");
        pictures.add("Fire");
        pictures.add("Mushroom");
        pictures.add("Pikachu");
        pictures.add("Bitcoin");
        pictures.add("Panda");
        pictures.add("Reindeer");
        pictures.add("Colosseum");
        new ConsoleInterface();
    }

    private void generator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 1 to generate from your path or different number to use ready pictures.");
        if (scanner.nextInt() == 1) {
            scanner.nextLine();
            System.out.println("Type the picture path");
            String path = scanner.nextLine();
            existingChecker(path);
            System.out.println("Type any number if you want to choose level or type 0 if you want to choose sizes manually.");
            if (scanner.nextInt() == 0) {
                sizeGenerator(scanner, path);
            } else {
                levelGenerator(scanner, path);
            }
        }
        else {
            readyPictures(scanner);
        }
    }

    private void readyPictures (Scanner scanner) {
        Scanner scanner1 =new Scanner(System.in);
        System.out.println("Choose the Picture. Currently avaiable:");
        System.out.println(pictures.toString());
        String path = "TestPictures/" + scanner1.nextLine()+".png";
        existingChecker(path);
        System.out.println("Type any number if you want to choose level or type 0 if you want to choose sizes manually.");
        if (scanner.nextInt() == 0) {
            sizeGenerator(scanner, path);
        } else {
            levelGenerator(scanner, path);
        }
    }

    private void solver() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int columns = sc.nextInt();
        ArrayList<Integer>[] nonogramArray = new ArrayList[rows + columns];
        for (int i = 0; i < nonogramArray.length; i++) {
            nonogramArray[i] = new ArrayList<Integer>();
        }

        System.out.println("Enter values for the nonogramArray (values separated by space, new line for new ArrayList in array): ");
        sc.nextLine();
        for (int i = 0; i < nonogramArray.length; i++) {
            String inputLine = sc.nextLine().trim();
            if (!inputLine.isEmpty()) {
                String[] input = inputLine.split(" ");
                for (String s : input) {
                    nonogramArray[i].add(Integer.parseInt(s));
                }
            }
        }

        new NonogramSolver(nonogramArray, rows);

        sc.close();
    }

    private void generatorWithSolver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the picture path");
        String path = scanner.nextLine();
        existingChecker(path);
        System.out.println("Type any number if you want to choose level or type 0 if you want to choose sizes manually.");
        if (scanner.nextInt() == 0) {
            sizeGeneratorWithSolver(scanner, path);

        } else {
            levelGeneratorWithSolver(scanner, path);
        }
    }

    //Method to let a user choose the level
    private void levelGenerator(Scanner scanner, String path) {
        System.out.println("Choose level from 1-4.");
        int level = scanner.nextInt();
        ArrayList<Integer>[] nonogramArray = new ArrayList[0];
        int[][] pixelArray = new int[0][0];
        if (level <= 0 || level > 4) {
            System.out.println("You need to choose a level from 0 to 3");
            levelGenerator(scanner, path);
        } else {
            new NonogramArrayFromPixelArray(path, level);
            nonogramArray = NonogramArrayFromPixelArray.getNonogramArray();
            pixelArray = NonogramArrayFromPixelArray.getPixelArray();
        }
        new NonogramExcelExporter(nonogramArray, pixelArray[1].length, "Nonogram");
        new NonogramArrayDisplay(nonogramArray, pixelArray[1].length);
    }

    private void levelGeneratorWithSolver(Scanner scanner, String path) {
        System.out.println("Choose level from 1-4.");
        int level = scanner.nextInt();
        ArrayList<Integer>[] nonogramArray = new ArrayList[0];
        int[][] pixelArray = new int[0][0];
        if (level <= 0 || level > 4) {
            System.out.println("You need to choose a level from 0 to 3");
            levelGenerator(scanner, path);
        } else {
            new NonogramArrayFromPixelArray(path, level);
            nonogramArray = NonogramArrayFromPixelArray.getNonogramArray();
            pixelArray = NonogramArrayFromPixelArray.getPixelArray();
        }
        new NonogramExcelExporter(nonogramArray, pixelArray[1].length, "Nonogram");
        new NonogramArrayDisplay(nonogramArray, pixelArray[1].length);
        new NonogramSolver(nonogramArray,pixelArray[1].length);
    }

    //Method to let a user choose the size
    private void sizeGenerator(Scanner scanner, String path){
        System.out.println("Type the number of columns and rows");
        int columns = scanner.nextInt();
        int rows = scanner.nextInt();
        new NonogramArrayFromPixelArray(path, columns, rows);
        ArrayList<Integer>[] nonogramArray = NonogramArrayFromPixelArray.getNonogramArray();
        int[][] pixelArray = NonogramArrayFromPixelArray.getPixelArray();
        new NonogramExcelExporter(nonogramArray, pixelArray[1].length, "Nonogram");
        new NonogramArrayDisplay(nonogramArray, pixelArray[1].length);
    }

    private void sizeGeneratorWithSolver(Scanner scanner, String path) {
        System.out.println("Type the number of columns and rows");
        int columns = scanner.nextInt();
        int rows = scanner.nextInt();
        new NonogramArrayFromPixelArray(path, columns, rows);
        ArrayList<Integer>[] nonogramArray = NonogramArrayFromPixelArray.getNonogramArray();
        int[][] pixelArray = NonogramArrayFromPixelArray.getPixelArray();
        new NonogramExcelExporter(nonogramArray, pixelArray[1].length, "Nonogram");
        new NonogramArrayDisplay(nonogramArray, pixelArray[1].length);
        new NonogramSolver(nonogramArray,pixelArray[1].length);
    }

    //Checks file existance
    private boolean existingChecker(String path){
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not found");
            new ConsoleInterface();
            return false;
        }
        return true;
    }
}
