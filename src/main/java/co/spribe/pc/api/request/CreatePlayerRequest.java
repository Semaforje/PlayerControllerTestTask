package co.spribe.pc.api.request;

import co.spribe.pc.api.constants.ConstantsURL;
import co.spribe.pc.RequestSpecFactory;
import co.spribe.pc.dto.PlayerDto;
import io.restassured.response.Response;

import static co.spribe.pc.TestDataHelper.getPlayerAsMap;
import static io.restassured.RestAssured.given;

public class CreatePlayerRequest extends AbstractRequest {

    public static Response createPlayerRequest(String editor, PlayerDto player){

        return given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParams(AbstractRequest.getPathParams(editor))
                .queryParams(getPlayerAsMap(player))
                .when()
                .get(ConstantsURL.CREATE_URI);
    }
}

