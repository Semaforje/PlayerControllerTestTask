package co.spribe.pc.read;

import co.spribe.pc.BaseTest;
import co.spribe.pc.api.constants.ConstantsIDs;
import co.spribe.pc.api.constants.ConstantsNames;
import co.spribe.pc.TestDataHelper;
import co.spribe.pc.dto.PlayerDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static co.spribe.pc.AssertionHelper.*;
import static co.spribe.pc.api.request.CreatePlayerRequest.createPlayerRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getAllPlayersRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;

@Epic("User API")
@Feature("Get Player Data")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class GetPlayerTest extends BaseTest {

    @Test
    @Story("Get player by Id")
    @Order(1)
    void getPlayerByIdTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response response = getPlayerRequest(p.as(PlayerDto.class));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, p.as(PlayerDto.class));
    }

    @Test
    @Story("Get admin by Id")
    void getAdminByIdTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response response = getPlayerRequest(p.as(PlayerDto.class));

        assertStatusCodeAndContentType(response);
        assertPlayerData(response,  p.as(PlayerDto.class));
    }

    @Test
    @Story("Get supervisor by Id")
    void getSupervisorByIdTest(){
        Response response = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);

        assertStatusCodeAndContentType(response);
    }

    @Test
    @Story("Get player by incorrectId")
    void getPlayerByIncorrectIdTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setId(0);
        Response response = getPlayerRequest(player);

        assertPlayerNotFound(response);
    }

    @Test
    @Story("Get all players")
    void getAllPlayersTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        Response response = getAllPlayersRequest();

        assertStatusCodeAndContentType(response);
        System.out.println("all players");
        System.out.println(response.asString());
        System.out.println(player.toString());
        assertPlayerDataIsPresent(response, player);
    }

}
