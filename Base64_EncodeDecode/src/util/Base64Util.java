package util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encoder(String val) {
        return Base64.getEncoder().encodeToString(val.getBytes(StandardCharsets.UTF_8));
    }

    public static String decoder(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}
