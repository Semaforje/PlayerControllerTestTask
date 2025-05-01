package co.spribe.pc;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

public class RequestSpecFactory {
    public static RequestSpecification getDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
