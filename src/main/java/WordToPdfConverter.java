import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class WordToPdfConverter {
    public static void convert(Properties properties) {
        String inputPath = properties.getProperty("input");
        String outputPath = properties.getProperty("output");

        try {
            File inputFile = new File(inputPath);

            // Load the Word document using Apache POI
            FileInputStream inputStream = new FileInputStream(inputFile);
            XWPFDocument wordDocument = new XWPFDocument(inputStream);
            inputStream.close();

            // Create a new PDF document
            PDDocument pdfDocument = new PDDocument();

            // Iterate over Word paragraphs and add text to PDF
            Iterator<XWPFParagraph> paragraphIterator = wordDocument.getParagraphsIterator();
            while (paragraphIterator.hasNext()) {
                XWPFParagraph paragraph = paragraphIterator.next();
                String paragraphText = paragraph.getText();

                // Create a new page in the PDF
                PDDocument pdfPage = new PDDocument();
                pdfDocument.addDocument(pdfPage);

                // Create a new PDPage and add it to the PDF page
                // You may need to adjust page size and margins as needed
                PDPage page = new PDPage();
                pdfPage.addPage(page);

                // Create a PDPageContentStream for the page
                PDPageContentStream contentStream = new PDPageContentStream(pdfPage, page);

                // Set the font and font size
                PDFont font = PDType1Font.HELVETICA_BOLD;
                float fontSize = 12;
                contentStream.setFont(font, fontSize);

                // Calculate the text position
                float margin = 50; // Adjust the margin as needed
                float yPosition = page.getMediaBox().getHeight() - margin;
                float width = page.getMediaBox().getWidth() - 2 * margin;
                float startX = page.getMediaBox().getLowerLeftX() + margin;

                // Add the paragraph text to the page
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, yPosition);
                contentStream.showText(paragraphText);
                contentStream.endText();
                contentStream.close();
            }

            // Save the PDF document to the specified output path
            FileOutputStream outputStream = new FileOutputStream(outputPath);
            pdfDocument.save(outputStream);
            outputStream.close();

            pdfDocument.close();

            System.out.println("Word document converted to PDF successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
