package co.spribe.pc.delete;

import co.spribe.pc.BaseTest;
import co.spribe.pc.Constants;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DeletePlayerTest extends BaseTest {
    Map<String, Integer> deleteUsers = createTestData();

    @Test
    void deleteUserAsPlayer(){
        deletePlayerTest(Constants.PLAYER,
                deleteUsers.get("supervisor"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.PLAYER,
                deleteUsers.get("admin"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.PLAYER,
                deleteUsers.get("player"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.PLAYER,
                testUsers.get("supervisor"),
                HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void deleteUserAsAdmin(){
        deletePlayerTest(Constants.ADMIN,
                deleteUsers.get("supervisor"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.ADMIN,
                deleteUsers.get("admin"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.ADMIN,
                deleteUsers.get("player"),
                HttpStatus.SC_OK);

        deletePlayerTest(Constants.ADMIN,
                testUsers.get("admin"),
                HttpStatus.SC_OK);
    }

    @Test
    void deleteUserAsSupervisor(){
        deletePlayerTest(Constants.SUPERVISOR,
                deleteUsers.get("supervisor"),
                HttpStatus.SC_BAD_REQUEST);

        deletePlayerTest(Constants.SUPERVISOR,
                deleteUsers.get("admin"),
                HttpStatus.SC_OK);

        deletePlayerTest(Constants.SUPERVISOR,
                testUsers.get("player"),    //because the player from deleteUsers was already deleted in previous test
                HttpStatus.SC_OK);

        deletePlayerTest(Constants.SUPERVISOR,
                testUsers.get("supervisor"),
                HttpStatus.SC_BAD_REQUEST);
    }


}
