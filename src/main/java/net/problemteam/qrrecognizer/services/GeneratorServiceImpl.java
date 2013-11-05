package net.problemteam.qrrecognizer.services;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceImpl implements GeneratorService {
    public File generateQrCode(String qrCode) {
        return new File("/images/some_image.png");
    }
}
