package co.spribe.pc;

import java.util.UUID;

public class TestDataHelper {

    public static String randomLogin() {
        return "user_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static String randomScreenName() {
        return "screen_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static String validPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
