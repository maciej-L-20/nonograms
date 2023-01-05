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
        resizedImage.getGraphics().drawImage(image, 0, 0, rows, columns, null);

        // Convert the image to black and white
        for (int y = 0; y < resizedImage.getHeight(); y++) {
            for (int x = 0; x < resizedImage.getWidth(); x++) {
                int rgb = resizedImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int avg = (r + g + b) / 3;
                if (avg < 128) {
                    resizedImage.setRGB(x, y, 0xff000000); // white
                } else {
                    resizedImage.setRGB(x, y, 0xffffffff); // black
                }
            }
        }

        // Save the processed image
        try {
            ImageIO.write(resizedImage, "png", new File(filepath + "_processed.png"));
        } catch (IOException e) {
            System.out.println("Error saving image.");
            return;
        }

        // Generate the 2D array
        pixelArray = new int[columns][rows];
        for (int y = 0; y < resizedImage.getHeight(); y++) {
            for (int x = 0; x < resizedImage.getWidth(); x++) {
                int rgb = resizedImage.getRGB(x, y);
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
    }
    public static int[][] getPixelArray() {
        return pixelArray;
    }

}
