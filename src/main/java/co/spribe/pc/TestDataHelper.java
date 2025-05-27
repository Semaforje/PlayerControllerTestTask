package co.spribe.pc;

import co.spribe.pc.api.constants.ConstantsNames;
import co.spribe.pc.dto.PlayerDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TestDataHelper {

    public static PlayerDto getRandomPlayer() {
        return getPlayerWithRole(ConstantsNames.ROLE_USER);
    }

    public static PlayerDto getRandomAdmin() {
        return getPlayerWithRole(ConstantsNames.ROLE_ADMIN);
    }

    public static PlayerDto getRandomSupervisor() {
        return getPlayerWithRole(ConstantsNames.ROLE_SUPERVISOR);
    }

    public static PlayerDto getPlayerWithRole(String role) {
        return new PlayerDto(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                role,
                randomScreenName()
        );
    }

    private static String getRandomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

    private static int getRandomValidAge() {
        Random random = new Random();
        return 16 + random.nextInt(60 - 16 + 1);
    }

    private static String randomLogin() {
        return "dn_user_" + getRandomString(6);
    }

    private static String randomScreenName() {
        return "dn_screen_" + getRandomString(6);
    }

    private static String validPassword() {
        return getRandomString( 8);
    }

    public static Map<String, Object> getPlayerAsMap(PlayerDto player){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.convertValue(player, Map.class);
        System.out.println("Query Params: " + map);
        return map;
    }
}
