import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

    private static int[][] pixelArray;

    public ImageProcessor(String filepath, int columns, int rows) {
        BufferedImage image = null;
        try {
            // Read in the original image
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("Error reading image file.");
            return;
        }
        // Convert the image to given resolution
        BufferedImage resizedImage = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(image, 0, 0, columns, rows, null);

        // Convert the image to black and white
        resizedImage=toMonochromatic(resizedImage);

        // Save the processed image
        try {
            ImageIO.write(resizedImage, "png", new File(filepath + "_processed.png"));
        } catch (IOException e) {
            System.out.println("Error saving image.");
            return;
        }

        // Get pixel array from generator
        pixelArray = pixelArrayGenerator(resizedImage);
    }
    //Another constructor for choosing the level instead of size
    public ImageProcessor(String filepath, int level) {
        BufferedImage image = null;
        try {
            // Read in the original image
            image = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("Error reading image file.");
            return;
        }
        // Scaling picture
        double scale = setScale(image,level);
        int processedHeight = (int) (image.getHeight() * scale);
        int processedWidth = (int) (image.getWidth() * scale);

        // Convert the image to given resolution
        BufferedImage resizedImage = new BufferedImage(processedWidth, processedHeight, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(image, 0, 0, processedWidth, processedHeight, null);
        // Convert to black and white
        resizedImage=toMonochromatic(resizedImage);

        // Save the processed image
        try {
            ImageIO.write(resizedImage, "png", new File(filepath + "_processed.png"));
        } catch (IOException e) {
            System.out.println("Error saving image.");
            return;
        }

        // Get pixel array from generator
        pixelArray = pixelArrayGenerator(resizedImage);
    }
        // Convert the image to black and white
    private static BufferedImage toMonochromatic(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int avg = (r + g + b) / 3;
                if (avg < 128) {
                    image.setRGB(x, y, 0xff000000); // white
                } else {
                    image.setRGB(x, y, 0xffffffff); // black
                }
            }
        }
        return image;
    }
    private int[][] pixelArrayGenerator(BufferedImage image){
        int width= image.getWidth();
        int height=image.getHeight();
        // Generate the 2D array
        pixelArray = new int[width][height];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                if (r == 0 && g == 0 && b == 0) {
                    pixelArray[x][y] = 1;
                } else {
                    pixelArray[x][y] = 0;
                }
            }
        }
        return pixelArray;
    }
    public static int[][] getPixelArray() {
        return pixelArray;
    }
    //Method to set scale
    private double setScale(BufferedImage image, int level){
        //Convert levels to sizes
        int sizes[] = {10, 30, 50, 100};
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        int largerDimension = Math.max(originalHeight, originalWidth);
        return  (double) sizes[level] / largerDimension;
    }
}
