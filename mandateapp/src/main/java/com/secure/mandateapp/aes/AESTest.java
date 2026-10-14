package com.secure.mandateapp.aes;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESTest {

    public static void main(String[] args) throws Exception{
        AesGcmExample aes = new AesGcmExample();

        String og = "Hrishikesh";

        String encrypted = aes.encrypt(og);

        System.out.println("Encrypted: "+ encrypted);

        String decrypted = aes.decrypt(encrypted);

        System.out.println("Decrypted: "+ decrypted);

        String secondTest = aes.encrypt("Testing for second Test..");

        System.out.println("Encrypted: "+ secondTest);

        byte[] encryptedBytes =
                Base64.getDecoder().decode(secondTest);

        // Modify one byte in the ciphertext.
        // The first 12 bytes are the IV.
        // The ciphertext starts at index 12.
        encryptedBytes[12] ^= 1;


        String tampered =  Base64.getEncoder()
                .encodeToString(encryptedBytes);

        try {
            String result = aes.decrypt(tampered);
            System.out.println("Decrypted: "+ result);
        } catch (Exception e) {
            System.out.println("Tampering Detected: "+ e.getClass().getName());
        }

    }

}
