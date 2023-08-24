//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class GenerateQrCode {
    public GenerateQrCode() {
    }

    public static InputStream generateQRCode(String qrCodeText) {
        InputStream is = null;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 400, 400, new HashMap<EncodeHintType, Object>() {{
                put(EncodeHintType.MARGIN, 2);
            }});
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "jpg", os);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException | WriterException var5) {
        }

        return is;
    }
}
