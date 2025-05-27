package co.spribe.pc;

import co.spribe.pc.dto.PlayerDto;
import co.spribe.pc.dto.PlayerDtoListWrapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Objects;


public class AssertionHelper {

    public static void assertStatusCodeAndContentType(Response response) {
        assertStatusCodeAndContentType(response, HttpStatus.SC_OK, ContentType.JSON);
    }

    public static void assertStatusCodeAndContentType(Response response, int code, ContentType type) {
        response.then().statusCode(code);
        response.then().contentType(type);
    }

    public static void assertPlayerData(Response response, PlayerDto player) {
        Assertions.assertEquals(player, response.as(PlayerDto.class));
    }

    public static void assertPlayerNotCreated(Response response) {
        assertStatusCode(response, HttpStatus.SC_BAD_REQUEST);
    }

    public static void assertStatusCode(Response response, int code) {
        response.then().statusCode(code);
    }

    public static void assertPlayerDeleted(Response response) {
        assertStatusCode(response, HttpStatus.SC_NO_CONTENT);
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

    public static void assertPlayerDataIsPresent(Response response, PlayerDto player) {
        PlayerDtoListWrapper wrapper = response.as(PlayerDtoListWrapper.class);
        List<PlayerDto> players = wrapper.getPlayers();
        assertPlayerPartialMatchInList(player, players);
    }

    public static void assertPlayerPartialMatchInList(PlayerDto player, List<PlayerDto> actualList) {
        boolean found = actualList.stream().anyMatch(actual ->
                Objects.equals(player.getAge(), actual.getAge()) &&
                        Objects.equals(player.getGender(), actual.getGender()) &&
                        Objects.equals(player.getScreenName(), actual.getScreenName())
        );

        Assertions.assertTrue(found, "Expected player not found in response list");
    }

}
