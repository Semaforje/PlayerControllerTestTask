package co.spribe.pc;

import co.spribe.pc.dto.PlayerDto;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static co.spribe.pc.AssertionHelper.assertPlayerNotFound;
import static co.spribe.pc.RequestSpecFactory.getDefaultSpec;
import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeEach
    public void setup() {
        request = getDefaultSpec();
    }

    public void getDeletedPlayerEmptyData(Response response){
        Response r = getPlayerRequest(response.as(PlayerDto.class));
        assertPlayerNotFound(r);
    }

}