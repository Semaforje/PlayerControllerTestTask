package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.dto.PlayerDto;
import co.spribe.pc.dto.PlayerIDDto;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetPlayerRequest extends AbstractRequest {

    public static Response getPlayerRequest(PlayerDto player) {
        PlayerIDDto pid = new PlayerIDDto(player.getId());

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(pid)
                .when()
                .post(ConstantsURL.GET_URI);
    }

    public static Response getPlayerRequest(Integer playerId) {
        PlayerIDDto pid = new PlayerIDDto(playerId);

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .body(pid)
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
