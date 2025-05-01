package co.spribe.pc.api.response;

import co.spribe.pc.dto.Player;
import io.restassured.response.Response;

public class CreatePlayerResponse {

    public static Player createPlayerResponse(Response response, Integer statusCode){
        response.then()
                .statusCode(statusCode);

        return response.as(Player.class);
    }
}
