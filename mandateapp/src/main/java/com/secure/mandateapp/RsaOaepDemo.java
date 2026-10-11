package com.secure.mandateapp;

import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class RsaOaepDemo {

    private static final OAEPParameterSpec OAEP_PARAMS =
            new OAEPParameterSpec(
                    "SHA-256",
                    "MGF1",
                    MGF1ParameterSpec.SHA256,
                    PSource.PSpecified.DEFAULT
            );

    private static byte[] encrypt(
            String plainText,
            PublicKey publicKey) throws Exception {

        Cipher cipher = Cipher.getInstance(
                "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"
        );

        cipher.init(
                Cipher.ENCRYPT_MODE,
                publicKey,
                OAEP_PARAMS
        );

        return cipher.doFinal(
                plainText.getBytes(StandardCharsets.UTF_8)
        );
    }

    private static String decrypt(
            byte[] encryptedBytes,
            PrivateKey privateKey) throws Exception {

        Cipher cipher = Cipher.getInstance(
                "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"
        );

        cipher.init(
                Cipher.DECRYPT_MODE,
                privateKey,
                OAEP_PARAMS
        );

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(
                decryptedBytes,
                StandardCharsets.UTF_8
        );
    }

    public static void main(String[] args) throws Exception {

        // STEP 1: Generate and save the key pair.
        KeyPair generatedPair = RsaKeyFiles.generateKeyPair();

        RsaKeyFiles.saveKeyPair(generatedPair);

        System.out.println("Key pair generated and saved.");

        // STEP 2: Reload the keys from disk.
        PublicKey publicKey = RsaKeyFiles.loadPublicKey();
        PrivateKey privateKey = RsaKeyFiles.loadPrivateKey();

        System.out.println("Keys loaded from disk.");

        // STEP 3: Encrypt a small test message.
        String originalMessage =
                "Practice RSA-OAEP encryption in Java";

        byte[] encryptedBytes = encrypt(
                originalMessage,
                publicKey
        );

        System.out.println(
                "Encrypted Base64: "
                + Base64.getEncoder().encodeToString(encryptedBytes)
        );

        // STEP 4: Decrypt using the matching private key.
        String decryptedMessage = decrypt(
                encryptedBytes,
                privateKey
        );

        System.out.println("Decrypted: " + decryptedMessage);

        if (originalMessage.equals(decryptedMessage)) {
            System.out.println("PASS: Original and decrypted text match.");
        } else {
            throw new AssertionError("Plaintext mismatch");
        }

        // STEP 5: Test the wrong private key.
        KeyPair wrongPair = RsaKeyFiles.generateKeyPair();

        try {
            String wrongDecryption = decrypt(
                    encryptedBytes,
                    wrongPair.getPrivate()
            );

            // If decryption returns, it must not be trusted automatically.
            if (originalMessage.equals(wrongDecryption)) {
                throw new AssertionError(
                        "Unexpected: wrong key recovered the message"
                );
            }

            throw new AssertionError(
                    "Unexpected decryption result with wrong key"
            );

        } catch (BadPaddingException
                 | IllegalBlockSizeException expected) {

            System.out.println(
                    "PASS: Wrong private key could not decrypt."
            );

        }

        // STEP 6: Verify that reloading preserved the public key bytes.
        if (Arrays.equals(
                generatedPair.getPublic().getEncoded(),
                publicKey.getEncoded())) {

            System.out.println("PASS: Public key survived serialization.");
        } else {
            throw new AssertionError("Public key changed after reload");
        }

        System.out.println("All expected checks completed.");
    }
}