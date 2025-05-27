package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.PlayerDto;
import co.spribe.pc.dto.PlayerIDDto;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class DeletePlayerRequest extends AbstractRequest {

    public static Response deletePlayerRequest(String editor, PlayerDto player){
        PlayerIDDto playerId = new PlayerIDDto(player.getId());

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParams(AbstractRequest.getPathParams(editor))
                .body(playerId)
                .when()
                .delete(ConstantsURL.DELETE_URI);
    }
}
