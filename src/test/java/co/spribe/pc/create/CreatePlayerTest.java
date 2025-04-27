package co.spribe.pc.create;

import co.spribe.pc.BaseTest;
import co.spribe.pc.TestDataHelper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import co.spribe.pc.Constants;

public class CreatePlayerTest extends BaseTest {

    @Test
    void createNewUserAsSupervisorTest(){
        createPlayerTest(Constants.SUPERVISOR,
                16,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.SUPERVISOR,
                60,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.SUPERVISOR,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "supervisor",
                HttpStatus.SC_OK);
    }

    @Test
    void createNewUserAsAdminTest(){
        createPlayerTest(Constants.ADMIN,
                16,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.ADMIN,
                60,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.ADMIN,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "supervisor",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewUserAsPlayerTest(){
        createPlayerTest(Constants.PLAYER,
                16,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.PLAYER,
                60,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.PLAYER,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "supervisor",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewPlayerWithDuplicateLoginTest(){
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "female",
                "duplicate_test",
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "female",
                "duplicate_test",
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "female",
                "duplicate_test",
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "female",
                "duplicate_test",
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "supervisor",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewPlayerWithDuplicateScreenNameTest(){
        createPlayerTest(Constants.OG_SUPERVISOR,
                16,
                "female",
                TestDataHelper.randomLogin(),
                "duplicate_screen",
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_OK);

        createPlayerTest(Constants.OG_SUPERVISOR,
                16,
                "female",
                TestDataHelper.randomLogin(),
                "duplicate_screen",
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                16,
                "female",
                TestDataHelper.randomLogin(),
                "duplicate_screen",
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                16,
                "female",
                TestDataHelper.randomLogin(),
                "duplicate_screen",
                TestDataHelper.validPassword(),
                "supervisor",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewPlayerWithIncorrectPasswordTest(){
        // < 7 characters
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName()+ "_6",
                "1q2w3e",
                "user",
                HttpStatus.SC_BAD_REQUEST);
        // > 15 characters
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName()+ "_16",
                "1q2w3e4r5t6y7u8i",
                "user",
                HttpStatus.SC_BAD_REQUEST);
        // special symbols
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName()+ "_ss",
                "!q2w3e$r",
                "user",
                HttpStatus.SC_BAD_REQUEST);
        // cyrillic
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName()+ "_cy",
                "Фq2w3e4Ы",
                "user",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewPlayerWithIncorrectAgeTest(){
        createPlayerTest(Constants.OG_SUPERVISOR,
                15,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                61,
                "male",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void createNewPlayerWithIncorrectGenderTest(){
        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "invalid",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "user",
                HttpStatus.SC_BAD_REQUEST);

        createPlayerTest(Constants.OG_SUPERVISOR,
                25,
                "invalid",
                TestDataHelper.randomLogin(),
                TestDataHelper.randomScreenName(),
                TestDataHelper.validPassword(),
                "admin",
                HttpStatus.SC_BAD_REQUEST);
    }

}
