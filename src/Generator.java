public class Generator extends NonogramArrayFromPixelArray {
    Generator(String path,int columns, int rows){
        super(path, columns, rows);
    }
    public static void main(String[] args) {
        Generator generator = new Generator("Zrzut ekranu 2023-01-5 o 17.05.04.png", 100, 120);
    }
}
