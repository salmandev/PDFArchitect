import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PdfSplitter {
    public static void split(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputDir = properties.getProperty("output");

        try {
            File inputFile = new File(inputPath);
            PDDocument document = Loader.loadPDF(inputFile);

            // Iterate through each page and save it as a separate PDF file
            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                PDDocument singlePageDocument = new PDDocument();
                singlePageDocument.addPage(document.getPage(pageIndex));

                String outputFileName = outputDir + File.separator + "page_" + (pageIndex + 1) + ".pdf";
                singlePageDocument.save(outputFileName);

                // Close the single page document
                singlePageDocument.close();
            }

            // Close the original PDF document
            document.close();

            System.out.println("PDF split into individual pages successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
