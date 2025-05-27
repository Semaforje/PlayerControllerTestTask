package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.dto.PlayerDto;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class UpdatePlayerRequest extends AbstractRequest {

    public static Response updatePlayerRequest(String editor, PlayerDto ogPlayer, PlayerDto newPlayer){

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParams(AbstractRequest.getPathParams(editor, ogPlayer.getId()))
                .body(newPlayer)
                .when()
                .patch(ConstantsURL.UPDATE_URI);
    }
}
