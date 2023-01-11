import java.util.Scanner;

public class Generator {
    Generator(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type the picture path");
        String path = scanner.nextLine();
        System.out.println("Type any number if you want to choose level or type 2 if you want to choose sizes manually.");
        if(scanner.nextInt()==2) {
            System.out.println("Type the number of columns and rows");
            int columns = scanner.nextInt();
            int rows = scanner.nextInt();
            NonogramArrayFromPixelArray nonogram = new NonogramArrayFromPixelArray(path, columns, rows);
        }
        else {
            System.out.println("Choose level from 0-3.");
            scanner.reset();
            int level=scanner.nextInt();
            if (level<0||level>3) {
                System.out.println("You need to choose a level from 0 to 3");
            }else {
                NonogramArrayFromPixelArray nonogram= new NonogramArrayFromPixelArray(path,level);
            }
        }
    }
    public static void main(String[] args) {
        Generator generator = new Generator();
    }

}