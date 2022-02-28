package view;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class ResourceLoader {
    public static InputStream loadResource(String resName){
        return ResourceLoader.class.getClassLoader().getResourceAsStream(resName);
    }

    public static Image loadImage(String resName) throws IOException{
        URL url = ResourceLoader.class.getClassLoader().getResource(resName);
        return ImageIO.read(url);
    }
    public static BufferedImage rotate(BufferedImage img , int degree)
    {

        // Getting Dimensions of image
        int width = img.getWidth();
        int height = img.getHeight();

        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();

        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(degree), width / 2,
                height / 2);
        g2.drawImage(img, null, 0, 0);

        // Return rotated buffer image
        return newImage;
    }
}
