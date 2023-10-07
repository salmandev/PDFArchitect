import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PDFArchitect {
    public static void main(String[] args) {
        Properties properties = new Properties();

        try {
            FileInputStream input = new FileInputStream("src/main/resources/pdfarchitect.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String action = properties.getProperty("action");

        switch (action.toLowerCase()) {
            case "merge":
                PdfMerger.merge(properties);
                break;
            case "split":
                PdfSplitter.split(properties);
                break;
            case "pdf-to-image":
                PdfToImageConverter.convert(properties);
                break;
            case "image-to-pdf":
                ImageToPdfConverter.convert(properties);
                break;
            case "word-to-pdf":
                WordToPdfConverter.convert(properties);
                break;
            case "pdf-to-word":
                PdfToWordConverter.convert(properties);
                break;
            default:
                System.out.println("Invalid action specified in properties file.");
        }
    }
}
