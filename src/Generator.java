import java.util.Scanner;

public class Generator {
    Generator(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type the picture path");
        String path = scanner.nextLine();
        System.out.println("Type any number if you want to choose level or type 0 if you want to choose sizes manually.");
        if(scanner.nextInt()==0) {
            sizeGenerator(scanner,path);
        }
        else {
            levelGenerator(scanner, path);
        }
    }
    //Method to let a user choose the level
    private void levelGenerator(Scanner scanner, String path){
        System.out.println("Choose level from 0-3.");
        int level=scanner.nextInt();
        if (level<0||level>3) {
            System.out.println("You need to choose a level from 0 to 3");
            levelGenerator(scanner, path);
        }else {
            new NonogramArrayFromPixelArray(path,level);
        }
    }
    //Method to let a user choose the size
    private void sizeGenerator(Scanner scanner, String path){
        System.out.println("Type the number of columns and rows");
        int columns = scanner.nextInt();
        int rows = scanner.nextInt();
        new NonogramArrayFromPixelArray(path, columns, rows);
    }
    public static void main(String[] args) {
        new Generator();
    }
}