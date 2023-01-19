package courierTests;

import generatingOfClasses.GeneratingCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pojo.CreateCourier;
import pojo.LoginCourier;
import steps.CourierSteps;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


@DisplayName("Логин  курьера")
public class LoginCourierTests {

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Ожидаемый код ответа: 200")
    public void loginCourierWithAllValidParams() {
        CreateCourier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), request.getPassword())));

        loginResponse.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера под несуществующим пользователем")
    @Description("Ожидаемый код ответа: 404")
    public void loginNonExistentCourier() {
        Response loginResponse = CourierSteps.loginCourier(new LoginCourier(
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера если неправильно указать логин")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithoutLogin() {
        CreateCourier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(null, request.getPassword())));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера если неправильно указать пароль")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithoutPassword() {
        CreateCourier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), null)));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}
//