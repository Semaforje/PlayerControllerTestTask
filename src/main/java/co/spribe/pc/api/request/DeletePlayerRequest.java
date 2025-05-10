package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.Player;
import io.qameta.allure.restassured.AllureRestAssured;

import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class DeletePlayerRequest implements Request {

    private int playerId;

    public DeletePlayerRequest(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public static Response deletePlayerRequest(String editor, Player player){
        DeletePlayerRequest deleteRequest = new DeletePlayerRequest(player.getId());

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParam(Constants.EDITOR_QUERY_PARAM, editor)
                .body(deleteRequest)
                .when()
                .delete(Constants.DELETE_URI);
    }
}
