package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.api.Request;
import co.spribe.pc.dto.PlayerDto;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class DeletePlayerRequest extends AbstractRequest {

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

    public static Response deletePlayerRequest(String editor, PlayerDto player){
        DeletePlayerRequest deleteRequest = new DeletePlayerRequest(player.getId());

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParams(AbstractRequest.getPathParams(editor))
                .body(deleteRequest)
                .when()
                .delete(ConstantsURL.DELETE_URI);
    }
}
