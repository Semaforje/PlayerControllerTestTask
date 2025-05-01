//package co.spribe.pc.update;
//
//import co.spribe.pc.BaseTest;
//import co.spribe.pc.TestDataHelper;
//import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.Test;
//import co.spribe.pc.Constants;
//
//import java.util.Map;
//
//public class UpdatePlayerTest extends BaseTest {
//    Map<String, Integer> updateUsers = createTestData();
//
//    @Test
//    void updateUserAsSupervisorTest(){
//        updatePlayerTest(Constants.SUPERVISOR,
//                updateUsers.get("player"),
//                16,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                updateUsers.get("admin"),
//                60,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                updateUsers.get("supervisor"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "supervisor",
//                HttpStatus.SC_OK);
//    }
//
//    @Test
//    void updateUserAsAdminTest(){
//        updatePlayerTest(Constants.ADMIN,
//                updateUsers.get("player"),
//                16,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                updateUsers.get("admin"),
//                60,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                updateUsers.get("superuser"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "supervisor",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updateUserAsPlayerTest(){
//        updatePlayerTest(Constants.PLAYER,
//                updateUsers.get("player"),
//                16,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.PLAYER,
//                updateUsers.get("admin"),
//                60,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.PLAYER,
//                updateUsers.get("superuser"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "supervisor",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerAsThemselvesTest(){
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                16,
//                "male",
//                Constants.PLAYER,
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                17,
//                null,
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                null,
//                "female",
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                null,
//                null,
//                null,
//                TestDataHelper.randomScreenName(),
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                null,
//                null,
//                null,
//                null,
//                TestDataHelper.validPassword(),
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "admin",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.PLAYER,
//                testUsers.get("player"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "supervisor",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updateAdminAsThemselvesTest(){
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                16,
//                "male",
//                Constants.ADMIN,
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                17,
//                null,
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                null,
//                "female",
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                null,
//                null,
//                null,
//                TestDataHelper.randomScreenName(),
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                null,
//                null,
//                null,
//                null,
//                TestDataHelper.validPassword(),
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.ADMIN,
//                testUsers.get("admin"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "supervisor",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updateSupervisorAsThemselvesTest(){
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                16,
//                "male",
//                Constants.SUPERVISOR,
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                17,
//                null,
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                null,
//                "female",
//                null,
//                null,
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                null,
//                null,
//                null,
//                TestDataHelper.randomScreenName(),
//                null,
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                null,
//                null,
//                null,
//                null,
//                TestDataHelper.validPassword(),
//                null,
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.SUPERVISOR,
//                testUsers.get("supervisor"),
//                null,
//                null,
//                null,
//                null,
//                null,
//                "admin",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerWithDuplicateLoginTest(){
//        Integer player_1 = createPlayerTest(Constants.OG_SUPERVISOR,
//                25,
//                "female",
//                "duplicate_test",
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        Integer player_2 = createPlayerTest(Constants.OG_SUPERVISOR,
//                25,
//                "female",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                player_2,
//                25,
//                "female",
//                "duplicate_test",
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerWithDuplicateScreenNameTest(){
//        Integer player_1 = createPlayerTest(Constants.OG_SUPERVISOR,
//                25,
//                "female",
//                TestDataHelper.randomLogin(),
//                "duplicate_screen",
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//        Integer player_2 = createPlayerTest(Constants.OG_SUPERVISOR,
//                25,
//                "female",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_OK);
//
//
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                player_2,
//                16,
//                "female",
//                TestDataHelper.randomLogin(),
//                "duplicate_screen",
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerWithIncorrectPasswordTest(){
//        // < 7 characters
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName()+ "_6",
//                "1q2w3e",
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//        // > 15 characters
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName()+ "_16",
//                "1q2w3e4r5t6y7u8i",
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//        // special symbols
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName()+ "_ss",
//                "!q2w3e$r",
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//        // cyrillic
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                25,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName()+ "_cy",
//                "Фq2w3e4Ы",
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerWithIncorrectAgeTest(){
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                15,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                61,
//                "male",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//    @Test
//    void updatePlayerWithIncorrectGenderTest(){
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("user"),
//                25,
//                "invalid",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "user",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("admin"),
//                25,
//                "invalid",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_BAD_REQUEST);
//
//        updatePlayerTest(Constants.OG_SUPERVISOR,
//                updateUsers.get("supervisor"),
//                25,
//                "invalid",
//                TestDataHelper.randomLogin(),
//                TestDataHelper.randomScreenName(),
//                TestDataHelper.validPassword(),
//                "supervisor",
//                HttpStatus.SC_BAD_REQUEST);
//    }
//
//}
