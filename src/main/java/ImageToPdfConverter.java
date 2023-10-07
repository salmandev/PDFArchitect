import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ImageToPdfConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File inputFile = new File(inputPath);
            PDDocument document = Loader.loadPDF(inputFile);

            // Rest of your code for converting images to PDF goes here

            document.close();

            System.out.println("Images converted to PDF successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
