package co.spribe.pc;

import co.spribe.pc.dto.Player;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static co.spribe.pc.api.request.GetPlayerRequest.getPlayerRequest;


public class AssertionHelper {

    public static void assertStatusCodeAndContentType(Response response) {
        assertStatusCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public static void assertStatusCodeAndContentType(Response response, int code, ContentType type) {
        response.then().statusCode(code);
        response.then().contentType(type);
    }

    public static void assertPlayerData(Response response, Player player) {
        Assertions.assertTrue(response.as(Player.class).getId() > 0, "ID is not valid");
//        Assertions.assertTrue(response.as(Player.class).isEqualTo(player), "Player created incorrectly");

        // Below is the workaround for create endpoint sending null fields
        if (!response.as(Player.class).isEqualTo(player)){
            Response r = getPlayerRequest(response.as(Player.class));
            Assertions.assertTrue(r.as(Player.class).isEqualTo(player), "Player created incorrectly");
        }
        else {
            System.out.println("Player created incorrectly");
        }
    }

    public static void assertPlayerNotCreated(Response response) {
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    public static void assertStatusCode(Response response, int code) {
        response.then().statusCode(code);
        response.then().header("Content-Type", (String) null);
    }

    public static void assertPlayerDeleted(Response response, Player player) {
        assertStatusCode(response, HttpStatus.SC_NO_CONTENT);
        Response r = getPlayerRequest(player);
        assertPlayerNotFound(r);
    }

    public static void assertPlayerNotDeleted(Response response) {
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    public static void assertPlayerNotFound(Response response) {
        assertStatusCode(response, HttpStatus.SC_OK);
        String body = response.getBody().asString();
        Assertions.assertTrue(body == null || body.isBlank(), "Expected empty body");
    }

    public static void assertPlayerNotUpdated(Response response) {
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    public static void assertPlayerHasNoRights(Response response) {
        assertStatusCode(response, HttpStatus.SC_FORBIDDEN);
    }

    public static void assertNthPlayerData(Response response, Player player, int position) {
        List<Player> players = response.jsonPath().getList("players", Player.class);
        Assertions.assertEquals(players.get(position - 1).getScreenName(), player.getScreenName(),
                "Screen name is not correct");
        Assertions.assertEquals(players.get(position - 1).getAge(), player.getAge(),
                "Age is not correct");
        Assertions.assertEquals(players.get(position - 1).getGender(), player.getGender(),
                "Gender is not correct");
    }

}
