package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.NonNull;

import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE;
import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE_STATUS_CODE;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.JSON;
import static java.nio.charset.StandardCharsets.UTF_8;

public class RestAssuredHelper {

    public Response getRequest(RequestSpecification reqSpec, @NonNull String endPoint, @NonNull String id) {
        return given()
                .spec(reqSpec)
                .when()
                .get(endPoint + id);
    }

    protected Response postRequest(RequestSpecification reqSpec, @NonNull String endPoint, @NonNull Object request) {
        return given()
                .spec(reqSpec)
                .body(request)
                .when()
                .post(endPoint);
    }

    protected Response deleteRequest(RequestSpecification reqSpec, @NonNull String endPoint, @NonNull String id) {
        return given()
                .spec(reqSpec)
                .when()
                .delete(endPoint + id);
    }

    public RequestSpecification setupRequestSpecification(@NonNull String url, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + token)
                .setConfig(config().encoderConfig(encoderConfig().defaultContentCharset(UTF_8)))
                .setContentType(JSON)
                .setUrlEncodingEnabled(false)
                .setAccept(JSON)
                .log(LogDetail.ALL)
                .build();
    }

    protected ResponseSpecification setupResponseSpecification() {
        return new io.restassured.builder.ResponseSpecBuilder()
                .expectContentType(JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public <T> void saveResponseToContext(ScenarioContext scenarioContext, Response rawResponse, Class<T> clazz) {
        scenarioContext.setContext(HTTP_RESPONSE_STATUS_CODE, rawResponse.statusCode());
        if (clazz != null) {
            try {
                Object parsedResponse = rawResponse
                        .then()
                        .spec(setupResponseSpecification())
                        .extract()
                        .body()
                        .as(clazz);
                scenarioContext.setContext(HTTP_RESPONSE, parsedResponse);
            } catch (Error e) {
                e.printStackTrace();
            }
        } else {
            scenarioContext.setContext(HTTP_RESPONSE, rawResponse.body().prettyPrint());
        }
    }
}
