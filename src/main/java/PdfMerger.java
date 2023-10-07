import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PdfMerger {
    public static void merge(Properties properties) {
        String[] inputPaths = properties.getProperty("input").split(",");
        String outputPath = properties.getProperty("output");

        PDFMergerUtility pdfMerger = new PDFMergerUtility();

        try {
            for (String inputPath : inputPaths) {
                pdfMerger.addSource(new File(inputPath).toString()); // Convert the file path to a string
            }

            pdfMerger.setDestinationFileName(outputPath);
            pdfMerger.mergeDocuments(null, null); // Pass null for memory usage settings
            System.out.println("PDFs merged successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
