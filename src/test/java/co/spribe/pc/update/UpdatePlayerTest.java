package co.spribe.pc.update;

import co.spribe.pc.BaseTest;
import co.spribe.pc.TestDataHelper;
import co.spribe.pc.api.constants.ConstantsIDs;
import co.spribe.pc.api.constants.ConstantsNames;
import co.spribe.pc.api.constants.ConstantsTestData;
import co.spribe.pc.dto.PlayerDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static co.spribe.pc.AssertionHelper.*;
import static co.spribe.pc.api.request.CreatePlayerRequest.createPlayerRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;
import static co.spribe.pc.api.request.UpdatePlayerRequest.updatePlayerRequest;

@Epic("User API")
@Feature("Update Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class UpdatePlayerTest extends BaseTest {

    @Test
    @Story("Update player as player")
    void updatePlayerAsPlayerTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player as admin")
    void updatePlayerAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update player as supervisor")
    void updatePlayerAsSupervisorTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer();

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as player")
    void updateAdminAsPlayerTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomAdmin();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomAdmin();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update admin as admin")
    void updateAdminAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomAdmin();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        originalPlayer.prettyPrint();

        PlayerDto editedPlayer = TestDataHelper.getRandomAdmin();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        response.prettyPrint();

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as supervisor")
    void updateAdminAsSupervisorTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomAdmin();

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update supervisor as player")
    void updateSupervisorAsPlayerTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        Response s = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        PlayerDto editedPlayer = TestDataHelper.getRandomSupervisor();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                s.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Update supervisor as admin")
    void updateSupervisorAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response s = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        PlayerDto editedPlayer = TestDataHelper.getRandomSupervisor();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                s.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Update supervisor as supervisor")
    void updateSupervisorAsSupervisorTest(){
        Response s = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        PlayerDto editedPlayer = new PlayerDto().setAge(37);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                s.as(PlayerDto.class),
                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(PlayerDto.class).getAge(), editedPlayer.getAge());
    }

    @Test
    @Story("Update player as themselves")
    void updatePlayerAsThemselvesTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                editor.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as themselves")
    void updateAdminAsThemselvesTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomAdmin();

        Response response = updatePlayerRequest(editor.as(PlayerDto.class).getLogin(),
                                                editor.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update only age")
    void updateOnlyAgeTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = new PlayerDto().setAge(25);
        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(PlayerDto.class).getAge(), editedPlayer.getAge());
    }

    @Test
    @Story("Update only login")
    void updateOnlyLoginTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = new PlayerDto().setLogin("edited_login");

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(PlayerDto.class).getLogin(), editedPlayer.getLogin());
    }

    @Test
    @Story("Update only screen name")
    void updateOnlyScreenNameTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = new PlayerDto().setScreenName("edited_screen_name");

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(PlayerDto.class).getScreenName(), editedPlayer.getScreenName());
    }

    @Test
    @Story("Update only password")
    void updateOnlyPasswordTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = new PlayerDto().setPassword("password7");

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
//        Assertions.assertEquals(response.as(Player.class).getPassword(), editedPlayer.getPassword());
    }

    @Test
    @Story("Update only gender")
    void updateOnlyGenderTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = new PlayerDto().setGender("female");

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(PlayerDto.class).getGender(), editedPlayer.getGender());
    }

    @Test
    @Story("Update only role")
    void updateOnlyRoleTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player );
        PlayerDto editedPlayer = new PlayerDto().setRole("admin");

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertNotEquals(response.as(PlayerDto.class).getRole(), editedPlayer.getRole());
    }

    @Test
    @Story("Update player with duplicate login")
    void updatePlayerWithDuplicateLoginTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setLogin(ConstantsTestData.DUPLICATE_NAME);
        Response duplicate = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer().setLogin(ConstantsTestData.DUPLICATE_NAME);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with duplicate screen name")
    void updatePlayerWithDuplicateScreenNameTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setScreenName(ConstantsTestData.DUPLICATE_NAME);
        Response duplicate = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer().setScreenName(ConstantsTestData.DUPLICATE_NAME);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsTestData.SHORT_PASSWORD,
            ConstantsTestData.LONG_PASSWORD,
            ConstantsTestData.SPECIAL_SYMBOLS_PASSWORD})
    @Story("update a player with incorrect password")
    void updatePlayerWithIncorrectPasswordTest(String password){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer().setPassword(password);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @ParameterizedTest
    @ValueSource(ints = {ConstantsTestData.YOUNG_AGE, ConstantsTestData.OLD_AGE})
    @Story("Create a player with incorrect age")
    void updatePlayerWithIncorrectAgeTest(Integer age){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer().setAge(age);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with invalid gender")
    void updatePlayerWithInvalidGenderTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response originalPlayer = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto editedPlayer = TestDataHelper.getRandomPlayer().setGender(ConstantsTestData.INVALID_GENDER);

        Response response = updatePlayerRequest(ConstantsNames.OG_SUPERVISOR,
                                                originalPlayer.as(PlayerDto.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

}
