package co.spribe.pc.read;

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
import static co.spribe.pc.api.request.GetPlayerRequest.getAllPlayersRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;
import static co.spribe.pc.api.response.CreatePlayerResponse.createPlayerResponse;

@Epic("User API")
@Feature("Get Player Data")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class GetPlayerTest extends BaseTest {

    @Test
    @Story("Get player by Id")
    @Order(1)
    void getPlayerByIdTest(){
        Player player = TestDataHelper.randomPlayer();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response response = getPlayerRequest(p.as(Player.class));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);
    }

    @Test
    @Story("Get admin by Id")
    @Order(1)
    void getAdminByIdTest(){
        Player player = TestDataHelper.randomAdmin();
        Response p = createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));
        Response response = getPlayerRequest(p.as(Player.class));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player);
    }

    @Test
    @Story("Get supervisor by Id")
    @Order(1)
    void getSupervisorByIdTest(){
        Response response = getPlayerRequest(Constants.OG_SUPER_ID);

        assertStatusCodeAndContentType(response);
    }

    @Test
    @Story("Get player by incorrectId")
    @Order(1)
    void getPlayerByIncorrectIdTest(){
        Player player = TestDataHelper.randomPlayer();
        Response response = getPlayerRequest(player);

        assertPlayerNotFound(response);
    }

    @Test
    @Story("Get all players")
    @Order(1)
    void getAllPlayersTest(){
        Player player = TestDataHelper.randomPlayer();
        createPlayerResponse(createPlayer(Constants.OG_SUPERVISOR, player));

        Response response = getAllPlayersRequest();

        assertStatusCodeAndContentType(response);
        assertNthPlayerData(response, player, 10);
    }

}
