package co.spribe.pc;

import co.spribe.pc.dto.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TestDataHelper {

    public static Player randomPlayer() {
        return new Player(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                Constants.ROLE_PLAYER,
                randomScreenName()
        );
    }

    public static Player randomAdmin() {
        return new Player(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                Constants.ROLE_ADMIN,
                randomScreenName()
        );
    }

    public static Player randomSupervisor() {
        return new Player(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                Constants.ROLE_SUPERVISOR,
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

    public static Map<String, Object> getPlayerAsMap(Player player){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.convertValue(player, Map.class);
        return map;
    }
}
