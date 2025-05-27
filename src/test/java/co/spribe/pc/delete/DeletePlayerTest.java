package co.spribe.pc.delete;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static co.spribe.pc.AssertionHelper.*;
import static co.spribe.pc.api.request.CreatePlayerRequest.createPlayerRequest;
import static co.spribe.pc.api.request.DeletePlayerRequest.deletePlayerRequest;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;

@Epic("User API")
@Feature("Delete Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class DeletePlayerTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsNames.ROLE_USER,
            ConstantsNames.ROLE_ADMIN})
    @Story("Delete a player with user and admin roles as Player")
    void deleteUserAndAdminAsUserTest(String role){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getPlayerWithRole(role);
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        Response response = deletePlayerRequest(editor.as(PlayerDto.class).getLogin(), p.as(PlayerDto.class));

        assertPlayerNotDeleted(response); // TODO Bug user is deleted
    }

    @Test
    @Story("Delete supervisor as player")
    void deleteSupervisorAsPlayerTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response p = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        Response response = deletePlayerRequest(editor.as(PlayerDto.class).getLogin(), p.as(PlayerDto.class));

        assertPlayerHasNoRights(response);
    }

    @Test
    @Story("Delete player as admin")
    void deletePlayerAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomPlayer();
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        Response response = deletePlayerRequest(editor.as(PlayerDto.class).getLogin(), p.as(PlayerDto.class));

        assertPlayerDeleted(response);
        getDeletedPlayerEmptyData(p);
    }

    @Test
    @Story("Delete admin as admin")
    void deleteAdminAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        player = TestDataHelper.getRandomAdmin();
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        Response response = deletePlayerRequest(editor.as(PlayerDto.class).getLogin(), p.as(PlayerDto.class));

        assertPlayerNotDeleted(response); // TODO bug user is deleted
    }

    @Test
    @Story("Delete supervisor as admin")
    void deleteSupervisorAsAdminTest(){
        PlayerDto player = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response p = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        Response response = deletePlayerRequest(editor.as(PlayerDto.class).getLogin(), p.as(PlayerDto.class));

        assertPlayerHasNoRights(response);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsNames.ROLE_USER,
            ConstantsNames.ROLE_ADMIN})
    @Story("Delete a player with user and admin roles as Supervisor")
    void deleteUserAndAdminAsSupervisorTest(String role){
        PlayerDto player = TestDataHelper.getPlayerWithRole(role);
        Response p = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        Response response = deletePlayerRequest(ConstantsNames.OG_SUPERVISOR, p.as(PlayerDto.class));

        assertPlayerDeleted(response);
        getDeletedPlayerEmptyData(p);
    }

    @Test
    @Story("Delete supervisor as Supervisor")
    void deleteSupervisorAsSupervisorTest(){
        Response p = getPlayerRequest(ConstantsIDs.OG_SUPER_ID);
        Response response = deletePlayerRequest(ConstantsNames.OG_SUPERVISOR, p.as(PlayerDto.class));

        assertPlayerHasNoRights(response);
    }

}
