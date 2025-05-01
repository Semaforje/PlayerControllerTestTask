package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import io.qameta.allure.restassured.AllureRestAssured;

import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class DeletePlayerRequest {

    protected Response deletePlayerTest(String editor, Integer playerId){

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParam(Constants.EDITOR_QUERY_PARAM, editor)
                .body("""
                        {
                          "playerId": "%s",
                        }
                        """.formatted(playerId))
                .when()
                .delete(Constants.DELETE_URI);
    }
}
