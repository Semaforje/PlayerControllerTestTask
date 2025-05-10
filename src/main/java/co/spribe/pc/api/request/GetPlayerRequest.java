package co.spribe.pc.api.request;

import co.spribe.pc.Constants;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.Player;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetPlayerRequest implements Request {

    private int playerId;

    public GetPlayerRequest(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public static Response getPlayerRequest(Player player) {
        GetPlayerRequest getRequest = new GetPlayerRequest(player.getId());

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(getRequest)
                .when()
                .post(Constants.GET_URI);
    }

    public static Response getPlayerRequest(Integer playerId) {
        GetPlayerRequest getRequest = new GetPlayerRequest(playerId);

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(getRequest)
                .when()
                .post(Constants.GET_URI);
    }

    public static Response getAllPlayersRequest() {

        return given()
                .filter(new AllureRestAssured())
                .spec(RequestSpecFactory.getDefaultSpec())
                .when()
                .get(Constants.GET_ALL_URI);
    }
}
