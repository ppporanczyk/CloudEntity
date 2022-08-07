package petstore;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public abstract class ResourceManager {

    protected static RequestSpecification specification;
    protected static final int OK = 200;
    protected static final int NOT_FOUND = 404;

    @BeforeAll
    protected static void initSpecification(){
        specification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://swaggerpetstore.przyklady.javastart.pl/v2")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    protected void createResource(String endpoint, Object objectBody) {
        given()
            .spec(specification)
            .body(objectBody)
            .when()
            .post(endpoint)
            .then()
            .statusCode(200);
    }

    protected  <T> T getResourceAsDTO(String endpoint, Class<T> responseClass) {
        return given()
                .spec(specification)
                .when()
                .get(endpoint)
                .then()
                .statusCode(OK)
                .extract().as(responseClass);
    }

    protected void exceptGetResponseWithSuccess(String endpoint) {
        given()
            .spec(specification)
            .when()
            .get(endpoint)
            .then()
            .statusCode(OK);
    }

    protected void exceptGetResponseWithNotFoundCode(String endpoint) {
        given()
             .spec(specification)
             .when()
             .get(endpoint)
             .then()
             .statusCode(NOT_FOUND);
    }

    protected void  updateResourceWithSuccess(String endpoint, Object objectBody) {
        given()
            .spec(specification)
            .body(objectBody)
            .when()
            .put(endpoint)
            .then()
            .statusCode(OK);
    }

    protected  void updateResourceWithNotFoundResponse(String endpoint, Object objectBody) {
        given()
            .spec(specification)
            .body(objectBody)
            .when()
            .put(endpoint)
            .then()
            .statusCode(NOT_FOUND);
    }

    protected  void deleteResourceWithSuccess(String endpoint) {
        given()
            .spec(specification)
            .when()
            .delete(endpoint)
            .then()
            .statusCode(OK);
    }

    protected void deleteResourceWithNotFoundResponse(String endpoint) {
        given()
            .spec(specification)
            .when()
            .delete(endpoint)
            .then()
            .statusCode(NOT_FOUND);
    }
}
