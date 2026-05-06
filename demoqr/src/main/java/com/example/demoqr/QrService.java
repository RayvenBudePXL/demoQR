package com.example.demoqr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

@Service
public class QrService {
    public BitMatrix generateQrCode(String data){
        BitMatrix bitMatrix =null;
        try{
            bitMatrix = new MultiFormatWriter()
                    .encode(data,BarcodeFormat.QR_CODE,250,250);

        }catch (WriterException e){
            e.printStackTrace();
        }
        return bitMatrix;
    }
    String data = "https://www.google.com";

}
