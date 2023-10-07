import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PdfToImageConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File file = new File(inputPath);
            PDDocument document = Loader.loadPDF(file);

            // Initialize the PDF renderer
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // Iterate through each page and convert it to an image
            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);

                // Save the image to the output directory
                String imageFileName = outputPath + File.separator + "page_" + (pageIndex + 1) + ".png";
                ImageIO.write(image, "PNG", new File(imageFileName));
            }

            // Close the PDF document when done
            document.close();

            System.out.println("PDF converted to images successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        Properties properties = new Properties();
        properties.setProperty("input", "path/to/input.pdf");
        properties.setProperty("output", "path/to/output/directory");

        PdfToImageConverter.convert(properties);
    }
}
