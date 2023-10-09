import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PdfToWordConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File inputFile = new File(inputPath);
            PDDocument pdfDocument = Loader.loadPDF(inputFile);

            // Create a new Word document
            XWPFDocument wordDocument = new XWPFDocument();

            // Iterate over PDF pages and extract text
            for (int page = 0; page < pdfDocument.getNumberOfPages(); page++) {
                String pageText = pdfDocument.getPage(page).getText();

                // Create a new paragraph in the Word document
                XWPFParagraph paragraph = wordDocument.createParagraph();
                XWPFRun run = paragraph.createRun();

                // Set the text content from the PDF page
                run.setText(pageText);
            }

            // Save the Word document to the specified output path
            FileOutputStream outputStream = new FileOutputStream(outputPath);
            wordDocument.write(outputStream);
            outputStream.close();

            pdfDocument.close();

            System.out.println("PDF converted to Word document successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
