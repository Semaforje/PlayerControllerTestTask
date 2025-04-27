package co.spribe.pc.read;

import co.spribe.pc.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class GetPlayerTest extends BaseTest {
    Map<String, Integer> getUsers = createTestData();

    @Test
    void getPlayerByIdTest(){
        getPlayerTest(getUsers.get("player"),
                HttpStatus.SC_OK);
    }

    @Test
    void getAdminByIdTest(){
        getPlayerTest(getUsers.get("player"),
                HttpStatus.SC_OK);
    }

    @Test
    void getSupervisorByIdTest(){
        getPlayerTest(getUsers.get("player"),
                HttpStatus.SC_OK);
    }

    @Test
    void getByIncorrectIdTest(){
        getPlayerTest(-1,
                HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void getAllUsersTest(){
        getAllPlayersTest(HttpStatus.SC_OK);
    }

}
