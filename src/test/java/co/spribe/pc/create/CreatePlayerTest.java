package co.spribe.pc.create;

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
import static co.spribe.pc.api.response.CreatePlayerResponse.createPlayerResponse;

@Epic("User API")
@Feature("Create Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class CreatePlayerTest extends BaseTest {

    @Test
    @Story("Create new player as supervisor")
    @Order(1)
    void createNewPlayerAsSupervisorTest(){
        Player player = TestDataHelper.randomPlayer();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player); // TODO bug: create endpoint returns null data
    }

    @Test
    @Story("Create new admin as supervisor")
    @Order(2)
    void createNewAdminAsSupervisorTest(){
        Player player = TestDataHelper.randomAdmin();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);
    }

    @Test
    @Story("Create new supervisor as supervisor")
    @Order(3)
    void createNewSupervisorAsSupervisorTest(){
        Player player = TestDataHelper.randomSupervisor();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create new player as admin")
    @Order(4)
    void createNewPlayerAsAdminTest(){
        Player admin = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, admin));
        Player player = TestDataHelper.randomPlayer();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), player));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);
    }

    @Test
    @Story("Create new admin as admin")
    @Order(5)
    void createNewAdminAsAdminTest(){
        Player admin = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, admin));
        Player player = TestDataHelper.randomAdmin();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), player));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);
    }

    @Test
    @Story("Create new supervisor as admin")
    @Order(6)
    void createNewSupervisorAsAdminTest(){
        Player admin = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, admin));
        Player player = TestDataHelper.randomSupervisor();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), player));

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create new player as player")
    @Order(7)
    void createNewPlayerAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player p = TestDataHelper.randomPlayer();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), p));

        assertPlayerHasNoRights(response); // TODO Bug: returns 403 instead of 400 like elsewhere
    }

    @Test
    @Story("Create new admin as player")
    @Order(8)
    void createNewAdminAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player p = TestDataHelper.randomAdmin();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), p));

        assertPlayerHasNoRights(response); // TODO Bug: returns 403 instead of 400 like elsewhere
    }

    @Test
    @Story("Create new supervisor as player")
    @Order(9)
    void createNewSupervisorAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Player p = TestDataHelper.randomSupervisor();
        Response response = createPlayerResponse(createPlayer(editor.as(Player.class).getLogin(), p));

        assertPlayerNotCreated(response);
    }

//    @Test
    @Story("Create a player with duplicated login")
    @Order(10)
    void createNewPlayerWithDuplicateLoginTest(){
        Player player = TestDataHelper.playerWithDuplicateLogin();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);

        player = TestDataHelper.playerWithDuplicateLogin();
        response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
         }

    @Test
    @Story("Create a player with duplicated screen name")
    @Order(11)
    void createNewPlayerWithDuplicateScreenNameTest(){
        Player player = TestDataHelper.playerWithDuplicateScreenName();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);

        response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

    @Test
    @Story("Create a player with short password")
    @Order(12)
    void createNewPlayerWithShortPasswordTest(){
        Player player = TestDataHelper.playerWithShortPassword();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

    @Test
    @Story("Create a player with long password")
    @Order(13)
    void createNewPlayerWithLongPasswordTest(){
        Player player = TestDataHelper.playerWithLongPassword();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

    @Test
    @Story("Create a player with special symbols in password")
    @Order(14)
    void createNewPlayerWithSpecialSymbolsPasswordTest(){
        Player player = TestDataHelper.playerWithInvalidPassword();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

    @Test
    @Story("Create a player 15 years old")
    @Order(15)
    void createNewPlayerWithYoungAgeTest(){
        Player player = TestDataHelper.playerWithYoungAge();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create a player 61 years old")
    @Order(16)
    void createNewPlayerWithOldAgeTest(){
        Player player = TestDataHelper.playerWithOldAge();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create a player with invalid gender")
    @Order(17)
    void createNewPlayerWithInvalidGenderTest(){
        Player player = TestDataHelper.playerWithInvalidGender();
        Response response = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

}
