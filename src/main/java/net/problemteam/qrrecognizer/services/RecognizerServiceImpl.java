package net.problemteam.qrrecognizer.services;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class RecognizerServiceImpl implements RecognizerService {
    public String recognizeQrCode(File qrCode) {
        return qrCode.getName();
    }
}
