import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RevImageProcessor {

    public static void RevImageProcessor(int[][] pixelArray, String filepath) {
        BufferedImage image = new BufferedImage(pixelArray.length, pixelArray[0].length, BufferedImage.TYPE_INT_ARGB);

        // Iterate through the pixel array and set the pixels black or white
        for (int y = 0; y < pixelArray[0].length; y++) {
            for (int x = 0; x < pixelArray.length; x++) {
                if (pixelArray[x][y] == 0) {
                    image.setRGB(x, y, 0xffffffff); // white
                } else {
                    image.setRGB(x, y, 0xff000000); // black
                }
            }
        }

        // Save the image to a file
        try {
            ImageIO.write(image, "png", new File(filepath));
        } catch (IOException e) {
            System.out.println("Error saving image.");
            return;
        }
    }
}
