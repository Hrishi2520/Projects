package com.secure.mandateapp.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AesGcmExample {

//    Selects AES in GCM mode without padding.
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final SecretKey secretKey;

//    Generates a cryptographically strong random nonce.
    private final SecureRandom secureRandom = new SecureRandom();

    public AesGcmExample() throws Exception {
//        Creates an AES key generator.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        Requests a 256-bit AES key.
        keyGenerator.init(256);
        this.secretKey = keyGenerator.generateKey();
    }

    public String encrypt(String plaintext) throws Exception {
        // create Vector to store secure random val
        byte[] iv = new byte[IV_LENGTH];
        secureRandom.nextBytes(iv);

        // cipher generation
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Configures a 128-bit authentication tag and the nonce.
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BITS, iv);

        // initiate cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        // Performs encryption or decryption and verifies the tag during decryption.
        byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        byte[] encrypted = new byte[IV_LENGTH + cipherText.length];

        System.arraycopy(iv, 0, encrypted, 0, IV_LENGTH);
        System.arraycopy(cipherText, 0, encrypted, IV_LENGTH, cipherText.length);

//        Converts binary data into a text-safe representation.
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encoded) throws Exception {

        // decode to encrypted bytes.
        byte[] decoded = Base64.getDecoder().decode(encoded);

        // 12 bytes for IV + 16 bytes for GCM authentication tag
        int minimumLength = IV_LENGTH + (TAG_LENGTH_BITS/8);

        if (decoded.length < minimumLength) {
            throw  new IllegalArgumentException("Invalid encrypted data: input is too short");
        }

        byte[] iv = new byte[IV_LENGTH];
        byte[] cipherText = new byte[decoded.length - IV_LENGTH];

        System.arraycopy(decoded,0, iv, 0, IV_LENGTH);
        System.arraycopy(decoded, IV_LENGTH, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BITS, iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
//          Performs encryption or decryption and verifies the tag during decryption.
        byte[] bytes = cipher.doFinal(cipherText);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
