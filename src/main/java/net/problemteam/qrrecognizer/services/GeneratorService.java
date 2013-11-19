package net.problemteam.qrrecognizer.services;

import java.io.File;

public interface GeneratorService {

    File generateQrCode(String qrCode) throws Exception;

}