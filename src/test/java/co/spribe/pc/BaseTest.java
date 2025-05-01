package co.spribe.pc;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
import static co.spribe.pc.RequestSpecFactory.getDefaultSpec;

public class BaseTest {
//    protected Map<String, Integer> testUsers = createTestData();

    protected RequestSpecification request;

    @BeforeEach
    public void setup() {
        request = getDefaultSpec();
    }
//    @BeforeAll
//    static void setup() {
//        request = getDefaultSpec();
//        RestAssured.baseURI = Constants.BASE_URL;
//    }

//    protected Map<String, Integer> createTestData(){
//        Map<String, Integer> pairs = new HashMap<>();
//
//        Integer supervisorId = createPlayerTest(Constants.OG_SUPERVISOR,
//                60,
//                "male",
//                Constants.SUPERVISOR,
//                Constants.SUPERVISOR,
//                TestDataHelper.validPassword(),
//                "supervisor",
//                HttpStatus.SC_OK);
//        pairs.put("supervisor", supervisorId);
//
//        Integer adminId = createPlayerTest(Constants.OG_SUPERVISOR,
//                60,
//                "male",
//                Constants.ADMIN,
//                Constants.ADMIN,
//                TestDataHelper.validPassword(),
//                "admin",
//                HttpStatus.SC_OK);
//        pairs.put("admin", adminId);
//
//        Integer playerId = createPlayerTest(Constants.OG_SUPERVISOR,
//                60,
//                "male",
//                Constants.PLAYER,
//                Constants.PLAYER,
//                TestDataHelper.validPassword(),
//                "player",
//                HttpStatus.SC_OK);
//        pairs.put("player", playerId);
//
//        return pairs;
//    }

//    protected Integer createPlayer(String editor, Integer age, String gender, String login, String screenName, String password, String role, Integer status){
//
//        var response = given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .pathParam("editor", editor) // login of editor
//                .queryParam("age", age)
//                .queryParam("gender", gender)
//                .queryParam("login", login)
//                .queryParam("screenName", screenName)
//                .queryParam("password", password)
//                .queryParam("role", role)
//                .when()
//                .get("/player/create/{editor}")
//                .then()
//                .log().all()
//                .statusCode(status)
//                .extract().jsonPath();
//
//        Map<String, Object> player = response.getMap("$");
//        assertThat(player.get("id")).isNotNull();
//        assertThat(player.get("age")).isEqualTo(age);
//        assertThat(player.get("gender")).isEqualTo(gender);
//        assertThat(player.get("login")).isEqualTo(login);
//        assertThat(player.get("screenName")).isEqualTo(screenName);
//        assertThat(player.get("password")).isEqualTo(password);
//        assertThat(player.get("role")).isEqualTo(role);
//
//        return (Integer) player.get("id");
//    }
//
//    protected void getPlayerTest(Integer playerId, Integer status){
//
//        var response = given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .body("""
//                        {
//                          "playerId": "%s",
//                        }
//                        """.formatted(playerId))
//                .when()
//                .post("/player/get/")
//                .then()
//                .log().all()
//                .statusCode(status)
//                .extract().jsonPath();
//
//        Integer id = response.getInt("id");
//        assertThat(playerId).isEqualTo(id);
//    }
//
//    protected void getAllPlayersTest(Integer status){
//
//        var response = given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/player/get/all")
//                .then()
//                .log().all()
//                .statusCode(status)
//                .extract().jsonPath();
//
//        List<Map<String, Object>> players = response.getList("players");
//
//        assertThat(players).isNotNull();
//        assertThat(players.size()).isGreaterThan(0);
//
//        Map<String, Object> firstPlayer = players.getFirst();
//
//        assertThat(firstPlayer).containsKeys("age", "gender", "id", "role", "screenName");
//        assertThat(firstPlayer.get("age")).isInstanceOf(Integer.class);
//        assertThat(firstPlayer.get("gender")).isInstanceOf(String.class);
//        assertThat(firstPlayer.get("id")).isInstanceOf(Integer.class);
//        assertThat(firstPlayer.get("role")).isInstanceOf(String.class);
//        assertThat(firstPlayer.get("screenName")).isInstanceOf(String.class);
//    }
//
//    protected void updatePlayerTest(String editor, Integer id, Integer age, String gender, String login, String screenName, String password, String role, Integer status){
//
//        var response = given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .pathParam("editor", editor) // login of editor
//                .pathParam("id", id)
//                .body("""
//                        {
//                          "age": "%s",
//                          "gender": "%s",
//                          "login": "%s",
//                          "password": "%s",
//                          "role": "%s",
//                          "screenName": "%s"
//                        }
//                        """.formatted(age, gender, login, password, role, screenName))
//                .when()
//                .patch("/player/update/{editor}/{id}")
//                .then()
//                .log().all()
//                .statusCode(status)
//                .extract().jsonPath();
//
//        Integer returnedId = response.getInt("id");
//        assertThat(returnedId).isNotNull();
//    }
//
//    protected void deletePlayerTest(String editor, Integer playerId, Integer status){
//
//        var response = given()
//                .filter(new AllureRestAssured())
//                .contentType(ContentType.JSON)
//                .pathParam("editor", editor)
//                .body("""
//                        {
//                          "playerId": "%s",
//                        }
//                        """.formatted(playerId))
//                .when()
//                .delete("/player/delete/{editor}")
//                .then()
//                .log().all()
//                .statusCode(status)
//                .extract().jsonPath();
//
//        Map<String, Object> body = response.getMap("body");
//        assertThat(body).isEmpty();
//        assertThat(response.getString("statusCode")).isEqualTo("100 CONTINUE");
//        assertThat(response.getInt("statusCodeValue")).isEqualTo(0);
//    }
}