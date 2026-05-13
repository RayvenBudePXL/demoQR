package com.example.demoqr;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QrController {

    private final DynamicQrService dynamicQrService;

    public QrController(DynamicQrService dynamicQrService) {
        this.dynamicQrService = dynamicQrService;
    }

    @CrossOrigin(origins = "${app.qr.frontend-origin}")
    @GetMapping
    public ResponseEntity<byte[]> generateQr() {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(dynamicQrService.generateQrPng());
        } catch (IllegalStateException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/access")
    public ResponseEntity<String> access(@RequestParam String token) {
        if (dynamicQrService.isTokenValid(token)) {
            return ResponseEntity.ok("Access granted to student profile");
        } else {
            return ResponseEntity.status(403).body("Invalid or expired QR");
        }
    }
}
