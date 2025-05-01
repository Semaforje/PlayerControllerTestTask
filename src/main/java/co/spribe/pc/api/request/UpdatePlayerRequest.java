package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.Player;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class UpdatePlayerRequest implements Request {

    protected Response updatePlayerTest(String editor, Integer id, Player player){

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParam(Constants.EDITOR_QUERY_PARAM, editor)
                .pathParam("id", id)
                .body(player)
                .when()
                .patch(Constants.UPDATE_URI);
    }
}
