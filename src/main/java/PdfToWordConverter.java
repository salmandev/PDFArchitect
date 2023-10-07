import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PdfToWordConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File file = new File(inputPath);
            PDDocument document = Loader.loadPDF(file);

            // Rest of your code for converting PDF to Word goes here

            document.close();

            System.out.println("PDF converted to Word document successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
