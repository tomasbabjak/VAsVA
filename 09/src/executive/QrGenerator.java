package executive;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Stateless
public class QrGenerator {

    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public static byte[] getQRCodeImage(String text) {
        byte[] pngData = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 20, 20);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

            Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            // pngData = pngOutputStream.toByteArray();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pngData;
    }
}
