package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.Player;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static co.spribe.pc.TestDataHelper.getPlayerAsMap;
import static io.restassured.RestAssured.given;

public class CreatePlayerRequest implements Request {

    public static Response createPlayer(String editor, Player player){

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParam(Constants.EDITOR_QUERY_PARAM, editor)
                .queryParams(getPlayerAsMap(player))
                .when()
                .get(Constants.CREATE_URI);
    }
}

