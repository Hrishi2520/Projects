package com.secure.mandateapp.aes;

public class AESTest {

    public static void main(String[] args) throws Exception{
        AesGcmExample aes = new AesGcmExample();

        String og = "Hrishikesh";

        String encrypted = aes.encrypt(og);

        System.out.println("Encrypted: "+ encrypted);

        String decrypted = aes.decrypt(encrypted);

        System.out.println("Decrypted: "+ decrypted);
    }

}
