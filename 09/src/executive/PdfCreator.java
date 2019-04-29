package executive;

import entity.Movie;
import entity.Screening;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Bean to create pdf contains information of reservation,seats,movie
 * Pdf also contains QRCode of Id of reservation
 */

@Stateless
public class PdfCreator {

    public static byte[] create(byte[] imageB, Movie movie, Screening screening, List<Integer> seats) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage(page);
        float HH = page.getMediaBox().getHeight();
        float WW = page.getMediaBox().getWidth();
        try {
            PDImageXObject image = PDImageXObject.createFromFile("D:\\FIIT\\4. semester\\VAVA\\VAsVA\\proxy.duckduckgo.com.png",document);
            //PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\minar\\Desktop\\VAVA_intellij\\proxy.duckduckgo.com.png",document);
            image.setHeight(120);
            image.setWidth((int)page.getMediaBox().getWidth());
            PDImageXObject qrimage = PDImageXObject.createFromByteArray(document,imageB,"qrcode");
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(image,0,0);
            contentStream.drawImage(qrimage,150,100);
            //
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 26);
            contentStream.newLineAtOffset(20, HH - 60 );
            String text = "CINEMAMAMA";
            contentStream.showText(text);
            contentStream.endText();
            //
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            String text2 = "Movie: " + movie.getTitle() + "     Movie lenght:" + movie.getDurationMin();
            contentStream.newLineAtOffset(20, HH - 80);
            contentStream.showText(text2);
            contentStream.endText();
            //
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            contentStream.newLineAtOffset(20, HH - 100 );
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("| ");
            for(Integer seat : seats){
                stringBuilder.append(seat.intValue() + " | ");
            }
            String text3 = "Seats: "  + stringBuilder.toString();
            contentStream.showText(text3);
            contentStream.endText();
            //
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            String text4 = "Auditorium: " + screening.getAuditorium().getName() + "     City: " + screening.getAuditorium().getCity().getCityName();
            contentStream.newLineAtOffset(20, HH - 120);
            contentStream.showText(text4);
            contentStream.endText();
            //
            contentStream.beginText();
            String cas = screening.getScreeningStart().toString();
            contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
            String text5 = "Screening date: " + cas;
            contentStream.newLineAtOffset(20, HH - 140);
            contentStream.showText(text5);
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
