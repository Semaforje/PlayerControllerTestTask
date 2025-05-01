package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.Player;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetPlayerRequest implements Request {

    protected Response getPlayerTest(Integer playerId, Player player) {

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .body("""
                        {
                          "playerId": "%s",
                        }
                        """.formatted(playerId))
                .when()
                .post(Constants.GET_URI);
    }

    protected Response getAllPlayersTest() {

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .when()
                .get(Constants.GET_ALL_URI);
    }
}
