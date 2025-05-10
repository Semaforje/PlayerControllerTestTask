package co.spribe.pc.update;

import co.spribe.pc.BaseTest;
import co.spribe.pc.TestDataHelper;
import co.spribe.pc.dto.Player;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import co.spribe.pc.Constants;

import static co.spribe.pc.AssertionHelper.*;
import static co.spribe.pc.api.request.CreatePlayerRequest.createPlayer;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;
import static co.spribe.pc.api.request.UpdatePlayerRequest.updatePlayerRequest;
import static co.spribe.pc.api.response.CreatePlayerResponse.createPlayerResponse;

@Epic("User API")
@Feature("Update Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class UpdatePlayerTest extends BaseTest {

    @Test
    @Story("Update player as player")
    @Order(1)
    void updatePlayerAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomPlayer();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player as admin")
    @Order(2)
    void updatePlayerAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomPlayer();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update player as supervisor")
    @Order(3)
    void updatePlayerAsSupervisorTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomPlayer();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as player")
    @Order(4)
    void updateAdminAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomAdmin();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomAdmin();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update admin as admin")
    @Order(5)
    void updateAdminAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomAdmin();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        originalPlayer.prettyPrint();

        Player editedPlayer = TestDataHelper.randomAdmin();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        response.prettyPrint();

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as supervisor")
    @Order(6)
    void updateAdminAsSupervisorTest(){
        Player player = TestDataHelper.randomAdmin();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomAdmin();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update supervisor as player")
    @Order(7)
    void updateSupervisorAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response s = getPlayerRequest(Constants.OG_SUPER_ID);
        Player editedPlayer = TestDataHelper.randomSupervisor();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                s.as(Player.class),
                                                editedPlayer);

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Update supervisor as admin")
    @Order(8)
    void updateSupervisorAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response s = getPlayerRequest(Constants.OG_SUPER_ID);
        Player editedPlayer = TestDataHelper.randomSupervisor();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                s.as(Player.class),
                                                editedPlayer);

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Update supervisor as supervisor")
    @Order(9)
    void updateSupervisorAsSupervisorTest(){
        Response s = getPlayerRequest(Constants.OG_SUPER_ID);
        Player editedPlayer = new Player().setAge(37);

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                s.as(Player.class),
                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(Player.class).getAge(), editedPlayer.getAge());
    }

    @Test
    @Story("Update player as themselves")
    @Order(10)
    void updatePlayerAsThemselvesTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomPlayer();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                editor.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update admin as themselves")
    @Order(11)
    void updateAdminAsThemselvesTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.randomAdmin();

        Response response = updatePlayerRequest(editor.as(Player.class).getLogin(),
                                                editor.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, editedPlayer);
    }

    @Test
    @Story("Update only age")
    @Order(12)
    void updateOnlyAgeTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setAge(25);
        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(Player.class).getAge(), editedPlayer.getAge());
    }

    @Test
    @Story("Update only login")
    @Order(13)
    void updateOnlyLoginTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setLogin("edited_login");

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(Player.class).getLogin(), editedPlayer.getLogin());
    }

    @Test
    @Story("Update only screen name")
    @Order(14)
    void updateOnlyScreenNameTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setScreenName("edited_screen_name");

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(Player.class).getScreenName(), editedPlayer.getScreenName());
    }

    @Test
    @Story("Update only password")
    @Order(15)
    void updateOnlyPasswordTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setPassword("password7");

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
//        Assertions.assertEquals(response.as(Player.class).getPassword(), editedPlayer.getPassword());
    }

    @Test
    @Story("Update only gender")
    @Order(16)
    void updateOnlyGenderTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setGender("female");

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertEquals(response.as(Player.class).getGender(), editedPlayer.getGender());
    }

    @Test
    @Story("Update only role")
    @Order(17)
    void updateOnlyRoleTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = new Player().setRole("admin");

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertStatusCodeAndContentType(response);
        Assertions.assertNotEquals(response.as(Player.class).getRole(), editedPlayer.getRole());
    }

    @Test
    @Story("Update player with duplicate login")
    @Order(18)
    void updatePlayerWithDuplicateLoginTest(){
        Player player = TestDataHelper.playerWithDuplicateLogin();
        Response duplicate = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithDuplicateLogin();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with duplicate screen name")
    @Order(19)
    void updatePlayerWithDuplicateScreenNameTest(){
        Player player = TestDataHelper.playerWithDuplicateScreenName();
        Response duplicate = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithDuplicateScreenName();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with short password")
    @Order(20)
    void updatePlayerWithShortPasswordTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithShortPassword();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with long password")
    @Order(21)
    void updatePlayerWithLongPasswordTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithLongPassword();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with invalid password")
    @Order(22)
    void updatePlayerWithInvalidPasswordTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithInvalidPassword();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with young age")
    @Order(23)
    void updatePlayerWithYoungAgeTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithYoungAge();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with old age")
    @Order(24)
    void updatePlayerWithOldAgeTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithOldAge();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

    @Test
    @Story("Update player with invalid gender")
    @Order(23)
    void updatePlayerWithInvalidGenderTest(){
        Player player = TestDataHelper.randomPlayer();
        Response originalPlayer = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player editedPlayer = TestDataHelper.playerWithInvalidGender();

        Response response = updatePlayerRequest(Constants.OG_SUPERVISOR,
                                                originalPlayer.as(Player.class),
                                                editedPlayer);

        assertPlayerNotUpdated(response); // TODO bug player is updated
    }

}
