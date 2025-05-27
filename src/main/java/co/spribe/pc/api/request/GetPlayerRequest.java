package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.dto.PlayerDto;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetPlayerRequest extends AbstractRequest {

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

    public static Response getPlayerRequest(PlayerDto player) {
        GetPlayerRequest getRequest = new GetPlayerRequest(player.getId());

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(getRequest)
                .when()
                .post(ConstantsURL.GET_URI);
    }

    public static Response getPlayerRequest(Integer playerId) {
        GetPlayerRequest getRequest = new GetPlayerRequest(playerId);

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(getRequest)
                .when()
                .post(ConstantsURL.GET_URI);
    }

    public static Response getAllPlayersRequest() {

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .when()
                .get(ConstantsURL.GET_ALL_URI);
    }
}
