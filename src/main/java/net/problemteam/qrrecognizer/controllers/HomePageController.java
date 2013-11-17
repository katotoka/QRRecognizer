package net.problemteam.qrrecognizer.controllers;

import java.io.File;
import java.io.IOException;

import net.problemteam.qrrecognizer.services.GeneratorService;
import net.problemteam.qrrecognizer.services.RecognizerService;
import net.problemteam.qrrecognizer.util.UniqueFileFactory;

import org.apache.log4j.Logger;
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
    private static final Logger log = Logger
                    .getLogger(HomePageController.class);

    @Value("${qrrecognizer.uploadPath}")
    private String imagesDirectoryPath;

    @Value("${qrrecognizer.generatedImagesPath}")
    private String generatedImagesPath;

    @Autowired
    private RecognizerService recognizerService;

    @Autowired
    private GeneratorService generatorService;

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(Model model) throws IOException {
        return "index";
    }

    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public String qrDecode(
                    @RequestParam("fileToDecode") MultipartFile fileToDecode,
                    Model model) throws Exception {

        File qrCodeFile = null;
        do {
            qrCodeFile = UniqueFileFactory.createUniqueFile(
                            imagesDirectoryPath, "/qrcode-", "");
        } while (qrCodeFile.exists());
        if (!qrCodeFile.createNewFile()) {
            throw new IOException("cannot create temporary file");
        }

        fileToDecode.transferTo(qrCodeFile);

        String decodedText = recognizerService.recognizeQrCode(qrCodeFile);

        if (!qrCodeFile.delete()) {
            log.error("Temporary file " + qrCodeFile.getAbsolutePath()
                            + " has not been deleted.");
        }

        model.addAttribute("decodedText", decodedText);
        return "decoded_text";
    }

    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public String qrEncode(@RequestParam("textToEncode") String textToEncode,
                    Model model) throws Exception {

        File encodedImageFile = this.generatorService
                        .generateQrCode(textToEncode);

        model.addAttribute("encodedImagePath",
                        "/generated/" + encodedImageFile.getName());
        return "encoded_image";
    }
}
