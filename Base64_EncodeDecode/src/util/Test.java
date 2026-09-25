package util;

import java.util.Base64;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String testVal = null;

        while (true) {
            getMenu();
            System.out.printf("Enter Text to Encrypt: \n");
            testVal = sc.nextLine();
            System.out.println("Entered Value: "+testVal);
            if (testVal.equalsIgnoreCase("0")) {
                System.out.println("Program about to stop...\n");
                try {
                    Thread.sleep(5000);
                    System.out.println("Program Stopped.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            System.out.println("Encoding...\n");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String encoded = Base64Util.encoder(testVal);

            System.out.printf("Encoded Text: %s \n",encoded);

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Decoding...\n");

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String decode = Base64Util.decoder(encoded);

            System.out.printf("Decoded Text: %s \n",decode);

            System.out.println("--------------------------------------------------\n");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void getMenu() {
        System.out.println("Enter Text or 0 to Exit.");
    }
}
