import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class WordToPdfConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File file = new File(inputPath);
            PDDocument document = Loader.loadPDF(file);

            // Rest of your code for converting Word to PDF goes here

            document.close();

            System.out.println("Word document converted to PDF successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
