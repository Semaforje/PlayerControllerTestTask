package co.spribe.pc;

import co.spribe.pc.dto.PlayerDto;

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

    public static PlayerDto randomPlayer() {
        return new PlayerDto(
                25,
                "male",
                randomLogin(),
                validPassword(),
                "user",
                randomScreenName()
        );
    }

    public static PlayerDto randomAdmin() {
        return new PlayerDto(
                25,
                "male",
                randomLogin(),
                validPassword(),
                "admin",
                randomScreenName()
        );
    }

    public static PlayerDto randomSupervisor() {
        return new PlayerDto(
                25,
                "male",
                randomLogin(),
                validPassword(),
                "supervisor",
                randomScreenName()
        );
    }
}
