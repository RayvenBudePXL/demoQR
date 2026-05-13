package com.example.demoqr;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/qr")
public class QrController {

    @Autowired
    private QrService qrService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<byte[]> generateQr() {
        try {
            String token = tokenService.createToken();

            String url = "http://localhost:8080/qr/access?token=" + token;

            BitMatrix bitMatrix = qrService.generateQrCode(url);

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

            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/access")
    public ResponseEntity<String> access(@RequestParam String token) {

        if (tokenService.isValid(token)) {
            return ResponseEntity.ok("Access granted to student profile");
        } else {
            return ResponseEntity.status(403).body("Invalid or expired QR");
        }
    }
}
