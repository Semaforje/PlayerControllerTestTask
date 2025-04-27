package co.spribe.pc.api;

import co.spribe.pc.dto.PlayerDto;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiClient {

    protected ValidatableResponse createPlayerTest(String editor, PlayerDto player){

        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .pathParam("editor", editor) // login of editor
                .queryParam("age", player.age)
                .queryParam("gender", player.gender)
                .queryParam("login", player.login)
                .queryParam("screenName", player.screenName)
                .queryParam("password", player.password)
                .queryParam("role", player.role)
                .when()
                .get("/player/create/{editor}")
                .then()
                .log().all();
//                .statusCode(status)
//                .extract().jsonPath();

//        Integer id = response.getInt("id");
//        assertThat(id).isNotNull();
//
//        return id;
    }
}
