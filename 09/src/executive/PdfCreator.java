package executive;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Stateless
public class PdfCreator {

    public static byte[] create() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A5);
        document.addPage(page);
        try {
            PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\minar\\Desktop\\VAVA_intellij\\proxy.duckduckgo.com.png",document);
            image.setHeight(50);
            image.setWidth((int)page.getMediaBox().getWidth());
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(image,0,0);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            contentStream.newLineAtOffset(20, 80);
            String text = "CINEMAMAMA";
            contentStream.showText(text);
            contentStream.endText();
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            String text2 = "Movie: Movie_title";
            contentStream.newLineAtOffset(20, image.getHeight() + 105);
            contentStream.showText(text2);
            contentStream.endText();
            contentStream.close();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();
            System.out.println("Vsetko ok");
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Je zle");
        return null;
    }


}
