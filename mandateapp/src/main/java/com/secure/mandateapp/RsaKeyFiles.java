package com.secure.mandateapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public final class RsaKeyFiles {

    private static final Path KEY_DIR = Paths.get("keys");

    private RsaKeyFiles() {
        // Utility class: prevent object creation.
    }

    public static KeyPair generateKeyPair()
            throws NoSuchAlgorithmException {

        KeyPairGenerator generator =
                KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        return generator.generateKeyPair();
    }

    public static void saveKeyPair(KeyPair keyPair)
            throws IOException {

        Files.createDirectories(KEY_DIR);

        String publicKeyBase64 = Base64.getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());

        String privateKeyBase64 = Base64.getEncoder()
                .encodeToString(keyPair.getPrivate().getEncoded());

        Files.writeString(
                KEY_DIR.resolve("public.key"),
                publicKeyBase64,
                StandardCharsets.US_ASCII
        );

        Files.writeString(
                KEY_DIR.resolve("private.key"),
                privateKeyBase64,
                StandardCharsets.US_ASCII
        );
    }

    public static PublicKey loadPublicKey()
            throws IOException, NoSuchAlgorithmException,
                   java.security.spec.InvalidKeySpecException {

        byte[] encoded = Base64.getDecoder().decode(
                Files.readString(
                        KEY_DIR.resolve("public.key"),
                        StandardCharsets.US_ASCII
                ).trim()
        );

        X509EncodedKeySpec keySpec =
                new X509EncodedKeySpec(encoded);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePublic(keySpec);
    }

    public static PrivateKey loadPrivateKey()
            throws IOException, NoSuchAlgorithmException,
                   java.security.spec.InvalidKeySpecException {

        byte[] encoded = Base64.getDecoder().decode(
                Files.readString(
                        KEY_DIR.resolve("private.key"),
                        StandardCharsets.US_ASCII
                ).trim()
        );

        PKCS8EncodedKeySpec keySpec =
                new PKCS8EncodedKeySpec(encoded);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePrivate(keySpec);
    }
}