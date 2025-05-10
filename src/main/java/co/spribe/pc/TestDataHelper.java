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
                randomScreenName() + "P"
        );
    }

    public static Player randomAdmin() {
        return new Player(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                Constants.ROLE_ADMIN,
                randomScreenName() + "A"
        );
    }

    public static Player randomSupervisor() {
        return new Player(
                getRandomValidAge(),
                "male",
                randomLogin(),
                validPassword(),
                Constants.ROLE_SUPERVISOR,
                randomScreenName() + "S"
        );
    }

    public static Player playerWithDuplicateLogin() {
        Player player = randomPlayer();
        player.setLogin("dn_duplicate");
        return player;
    }

    public static Player playerWithDuplicateScreenName() {
        Player player = randomPlayer();
        player.setScreenName("dn_duplicate");
        return player;
    }
    public static Player playerWithShortPassword() {
        Player player = randomPlayer();
        player.setPassword("1q2w3e");
        return player;
    }

    public static Player playerWithLongPassword() {
        Player player = randomPlayer();
        player.setPassword("1q2w3e4r5t6y7u8i");
        return player;
    }

    public static Player playerWithInvalidPassword() {
        Player player = randomPlayer();
        player.setPassword("!Q@W#E$R");
        return player;
    }

    public static Player playerWithYoungAge() {
        Player player = randomPlayer();
        player.setAge(15);
        return player;
    }

    public static Player playerWithOldAge() {
        Player player = randomPlayer();
        player.setAge(61);
        return player;
    }

    public static Player playerWithInvalidGender() {
        Player player = randomPlayer();
        player.setGender("invalid");
        return player;
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
        System.out.println("Query Params: " + map);
        return map;
    }
}
