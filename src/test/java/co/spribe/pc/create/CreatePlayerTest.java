package co.spribe.pc.create;

import co.spribe.pc.BaseTest;
import co.spribe.pc.TestDataHelper;
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

@Epic("User API")
@Feature("Create Player")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled("Temporarily excluded from test run")
public class CreatePlayerTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {
        ConstantsNames.ROLE_USER,
                ConstantsNames.ROLE_ADMIN})
    @Story("Create a player with user and admin roles as Supervisor")
    void createNewUserAnAdminAsSupervisorTest(String role){
        PlayerDto player = TestDataHelper.getPlayerWithRole(role);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player); // TODO bug: create endpoint returns null data
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsNames.ROLE_USER,
            ConstantsNames.ROLE_ADMIN})
    @Story("Create a player with user and admin roles as Admin")
    void createNewUserAnAdminAsAdminTest(String role){
        PlayerDto admin = TestDataHelper.getRandomAdmin();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, admin);
        PlayerDto player = TestDataHelper.getPlayerWithRole(role);
        Response response = createPlayerRequest(editor.as(PlayerDto.class).getLogin(), player);

        assertStatusCodeAndContentType(response);
        assertPlayerData(response, player); // TODO bug: create endpoint returns null data
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsNames.ROLE_USER,
            ConstantsNames.ROLE_ADMIN})
    @Story("Create a player with user and admin roles as User")
    void createNewUserAnAdminAsPlayerTest(String role){
        PlayerDto player = TestDataHelper.getRandomPlayer();
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto p = TestDataHelper.getPlayerWithRole(role);
        Response response = createPlayerRequest(editor.as(PlayerDto.class).getLogin(), p);

        assertPlayerNotCreated(response); // TODO Bug: returns 403 instead of 400 like elsewhere
    }

    @Test
    @Story("Create new supervisor as supervisor")
    void createNewSupervisorAsSupervisorTest(){
        PlayerDto player = TestDataHelper.getRandomSupervisor();
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        assertPlayerNotCreated(response);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsNames.ROLE_USER,
            ConstantsNames.ROLE_ADMIN})
    @Story("Create a player with supervisor role as User, Admin")
    void createNewUserAnAdminAsPlayerAndAdminTest(String role){
        PlayerDto player = TestDataHelper.getPlayerWithRole(role);
        Response editor = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        PlayerDto p = TestDataHelper.getPlayerWithRole(ConstantsNames.ROLE_SUPERVISOR);
        Response response = createPlayerRequest(editor.as(PlayerDto.class).getLogin(), p);

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create a player with duplicated login")
    void createNewPlayerWithDuplicateLoginTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setLogin(ConstantsTestData.DUPLICATE_NAME);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        assertStatusCodeAndContentType(response);

        PlayerDto newPlayer = TestDataHelper.getRandomPlayer().setLogin(ConstantsTestData.DUPLICATE_NAME);
        Response newResponse = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, newPlayer);
        assertPlayerNotCreated(newResponse); // TODO Bug: returns 200 instead of 400
         }

    @Test
    @Story("Create a player with duplicated screen name")
    void createNewPlayerWithDuplicateScreenNameTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setScreenName(ConstantsTestData.DUPLICATE_NAME);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);
        assertStatusCodeAndContentType(response);

        PlayerDto newPlayer = TestDataHelper.getRandomPlayer().setScreenName(ConstantsTestData.DUPLICATE_NAME);
        Response newResponse = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, newPlayer);
        assertPlayerNotCreated(newResponse); // TODO Bug: returns 200 instead of 400
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ConstantsTestData.SHORT_PASSWORD,
            ConstantsTestData.LONG_PASSWORD,
            ConstantsTestData.SPECIAL_SYMBOLS_PASSWORD})
    @Story("Create a player with incorrect password")
    void createNewPlayerWithIncorrectPasswordTest(String password){
        PlayerDto player = TestDataHelper.getRandomPlayer().setPassword(password);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }


    @ParameterizedTest
    @ValueSource(ints = {ConstantsTestData.YOUNG_AGE, ConstantsTestData.OLD_AGE})
    @Story("Create a player with incorrect age")
    void createNewPlayerWithIncorrectAgeTest(Integer age){
        PlayerDto player = TestDataHelper.getRandomPlayer().setAge(age);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        assertPlayerNotCreated(response);
    }

    @Test
    @Story("Create a player with invalid gender")
    void createNewPlayerWithInvalidGenderTest(){
        PlayerDto player = TestDataHelper.getRandomPlayer().setGender(ConstantsTestData.INVALID_GENDER);
        Response response = createPlayerRequest(ConstantsNames.OG_SUPERVISOR, player);

        assertPlayerNotCreated(response); // TODO Bug: returns 200 instead of 400
    }

}
