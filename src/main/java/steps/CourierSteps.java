package steps;

import config.ConfigForScooter;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CreateCourier;
import pojo.LoginCourier;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

public class CourierSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(ConfigForScooter.BASE_URL)
            .setBasePath("/courier")
            .setContentType(ContentType.JSON)
            .build();

    @Step("Создание курьера")
    public static Response createCourier(CreateCourier body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post();
    }

    @Step("Логин курьера в системе")
    public static Response loginCourier(LoginCourier body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post("/login");
    }

    public void deletedSuccessfully(CreateCourier response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("ok", is(true))

    }
//