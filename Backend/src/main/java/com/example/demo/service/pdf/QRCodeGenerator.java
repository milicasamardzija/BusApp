package com.example.demo.service.pdf;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRCodeGenerator {

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/images/QRCode";

    public void getQrCode(int id){

        String link="http://localhost:4200/ticket/" + id;
        try {
            System.out.println("Generisem QR kod.");
            QRCodeGenerator.generateQRCodeImage(link,250,250,QR_CODE_IMAGE_PATH + ".png");
            System.out.println("Iznegerisao sam QR kod.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
     }

    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
