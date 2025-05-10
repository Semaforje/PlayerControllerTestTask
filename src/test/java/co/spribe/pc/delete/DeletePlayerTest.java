package co.spribe.pc.delete;

import co.spribe.pc.BaseTest;
import co.spribe.pc.Constants;
import co.spribe.pc.TestDataHelper;
import co.spribe.pc.dto.Player;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static co.spribe.pc.AssertionHelper.*;
import static co.spribe.pc.api.request.CreatePlayerRequest.createPlayer;
import static co.spribe.pc.api.request.DeletePlayerRequest.deletePlayerRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;
import static co.spribe.pc.api.response.CreatePlayerResponse.createPlayerResponse;

@Epic("User API")
@Feature("Delete Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class DeletePlayerTest extends BaseTest {

    @Test
    @Story("Delete player as player")
    @Order(1)
    void deletePlayerAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerNotDeleted(response); // TODO Bug user is deleted
    }

    @Test
    @Story("Delete admin as player")
    @Order(2)
    void deleteAdminAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomAdmin();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerNotDeleted(response); //TODO Bug user is deleted
    }

    @Test
    @Story("Delete supervisor as player")
    @Order(3)
    void deleteSupervisorAsPlayerTest(){
        Player player = TestDataHelper.randomPlayer();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response p = getPlayerRequest(Constants.OG_SUPER_ID);
        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Delete player as admin")
    @Order(4)
    void deletePlayerAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomPlayer();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerDeleted(response, player);
    }

    @Test
    @Story("Delete admin as admin")
    @Order(5)
    void deleteAdminAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        player = TestDataHelper.randomAdmin();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerNotDeleted(response); // TODO bug user is deleted
    }

    @Test
    @Story("Delete supervisor as admin")
    @Order(6)
    void deleteSupervisorAsAdminTest(){
        Player player = TestDataHelper.randomAdmin();
        Response editor = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response p = getPlayerRequest(Constants.OG_SUPER_ID);
        Response response = deletePlayerRequest(editor.as(Player.class).getLogin(), p.as(Player.class));

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Delete player as Supervisor")
    @Order(7)
    void deletePlayerAsSupervisorTest(){
        Player player = TestDataHelper.randomPlayer();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response response = deletePlayerRequest(Constants.OG_SUPERVISOR, p.as(Player.class));

        assertPlayerDeleted(response, player);
    }

    @Test
    @Story("Delete admin as Supervisor")
    @Order(8)
    void deleteAdminAsSupervisorTest(){
        Player player = TestDataHelper.randomAdmin();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response response = deletePlayerRequest(Constants.OG_SUPERVISOR, p.as(Player.class));

        assertPlayerDeleted(response, player);
    }

    @Test
    @Story("Delete supervisor as Supervisor")
    @Order(9)
    void deleteSupervisorAsSupervisorTest(){
        Response p = getPlayerRequest(Constants.OG_SUPER_ID);
        Response response = deletePlayerRequest(Constants.OG_SUPERVISOR, p.as(Player.class));

        assertPlayerHasNoRights(response);
    }

}
