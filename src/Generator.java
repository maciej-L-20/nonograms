import java.util.Scanner;

public class Generator {
    Generator(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type the picture path");
        String path = scanner.nextLine();
        System.out.println("Type the number of columns and rows");
        int columns= scanner.nextInt();
        int rows= scanner.nextInt();
        NonogramArrayFromPixelArray nonogram = new NonogramArrayFromPixelArray(path,columns,rows);
    }
    public static void main(String[] args) {
        Generator generator = new Generator();
    }
}
