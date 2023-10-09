import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
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

            // Load an existing PDF or create a new one
            PDDocument document = new PDDocument();

            // Read the image file and convert it to PDF
            BufferedImage image = ImageIO.read(inputFile);
            if (image != null) {
                PDPage page = new PDPage();
                document.addPage(page);

                // Create a PDImageXObject from the BufferedImage
                PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);

                // Create a PDPageContentStream for the page
                PDPageContentStream contentStream = new PDPageContentStream(document, page);

                // Add the image to the page
                float scale = 1f; // Adjust the scale as needed
                contentStream.drawImage(pdImage, 0, 0, pdImage.getWidth() * scale, pdImage.getHeight() * scale);

                // Close the content stream and save the PDF
                contentStream.close();
                document.save(outputPath);

                System.out.println("Image converted to PDF successfully.");
            } else {
                System.err.println("Error: Unable to read the input image.");
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
