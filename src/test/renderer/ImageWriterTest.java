package test.renderer;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

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
        int width = 1600;
        int height = 1000;
        int nx = 800;
        int ny = 500;
        ImageWriter imageWriter = new ImageWriter(imageName, width, height, nx, ny);
        for (int col = 0; col < ny; col++)
            for (int row = 0; row < nx; row++)
                if (col % 50 == 0 || row % 50 == 0)
                    imageWriter.writePixel(row, col, new Color(java.awt.Color.YELLOW));

        imageWriter.writeToImage();

    }

} 
