package net.problemteam.qrrecognizer.services;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

@Service
public class RecognizerServiceImpl implements RecognizerService {
    public String recognizeQrCode(File qrCodeFile) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                        new BufferedImageLuminanceSource(bufferedImage)));
        return new QRCodeReader().decode(binaryBitmap).getText();
    }
}
