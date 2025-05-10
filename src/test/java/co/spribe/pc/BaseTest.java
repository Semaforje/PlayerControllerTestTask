package co.spribe.pc;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static co.spribe.pc.RequestSpecFactory.getDefaultSpec;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeEach
    public void setup() {
        request = getDefaultSpec();
    }

}