package com.example.demoqr;
import com.google.zxing.common.BitMatrix;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/qr")
public class QrController {

    @Autowired
    private QrService qrService;

    @GetMapping
    public ResponseEntity<byte[]> generateQr(@RequestParam String data) {
        try {
            BitMatrix bitMatrix = qrService.generateQrCode(data);

            BufferedImage image = new BufferedImage(
                    bitMatrix.getWidth(),
                    bitMatrix.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=qr.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
