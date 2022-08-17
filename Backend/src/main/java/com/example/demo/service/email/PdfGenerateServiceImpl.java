package com.example.demo.service.email;

import com.example.demo.service.pdf.QRCodeGenerator;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pdf.directory}")
    private String pdfDirectory;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/images/QRCode";

    @Override
    public void generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {
            System.out.println("Pisem pdf.");
            FileOutputStream fileOutputStream = new FileOutputStream(pdfDirectory + pdfFileName);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
            System.out.println("Napisao sam pdf.");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (com.lowagie.text.DocumentException e) {
            System.out.println(e);
        }
    }
}
