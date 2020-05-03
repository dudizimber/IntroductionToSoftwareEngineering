package test.renderer;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

/**
 * ImageWriter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>abr 30, 2020</pre>
 */
public class ImageWriterTest {

    /**
     * Test method for {@link ImageWriter#writeToImage()}
     */
    @Test
    public void testWriteToImage() {
        String imageName = "MyFirstImage";
        int width = 1000;
        int height = 1600;
        int nx = 500;
        int ny = 800;
        ImageWriter imageWriter = new ImageWriter(imageName, width, height, nx, ny);
        for (int col = 0; col < ny; col++)
            for (int row = 0; row < nx; row++)
                if (col % 10 == 0 || row % 10 == 0)
                    imageWriter.writePixel(row, col, Color.YELLOW);

        imageWriter.writeToImage();

    }

} 
