package net.problemteam.qrrecognizer.controllers;

import java.io.File;
import java.io.IOException;

import net.problemteam.qrrecognizer.services.GeneratorService;
import net.problemteam.qrrecognizer.services.RecognizerService;
import net.problemteam.qrrecognizer.util.UniqueFileFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomePageController {

    @Value("${qrrecognizer.imagesDirectoryPath}")
    private String imagesDirectoryPath;

    @Autowired
    private RecognizerService recognizerService;

    @Autowired
    private GeneratorService generatorService;

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(Model model) throws IOException {
        return "index";
    }

    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public String qrDecode(@RequestParam("fileToDecode")
    MultipartFile fileToDecode, Model model) throws IllegalStateException,
            IOException {

        File qrCodeFile = null;
        do {
            qrCodeFile = UniqueFileFactory.createUniqueFile(
                    imagesDirectoryPath, "qrcode-", ".png");
        } while (qrCodeFile.exists());
        if (!qrCodeFile.createNewFile()) {
            throw new IOException("cannot create temporary file");
        }

        fileToDecode.transferTo(qrCodeFile);

        String decodedText = recognizerService.recognizeQrCode(qrCodeFile);

        model.addAttribute("decodedText", decodedText);
        return "decoded_text";
    }

    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public String qrEncode(@RequestParam("textToEncode")
    String textToEncode, Model model) throws IOException {

        File encodedImageFile = this.generatorService
                .generateQrCode(textToEncode);

        model.addAttribute("encodedImagePath",
                encodedImageFile.getCanonicalPath());
        return "encoded_image";
    }
}
