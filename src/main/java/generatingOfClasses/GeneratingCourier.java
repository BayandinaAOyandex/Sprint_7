package generatingOfClasses;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreateCourier;
import steps.CourierSteps;


import static com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV.BASE_URI;
import static io.restassured.RestAssured.given;

public class GeneratingCourier {
    private int courierId;  // default value
    private final CourierSteps check = new CourierSteps();

    public static CreateCourier getNewCourier() {
        return new CreateCourier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier getNewCourierWithFirstNameNull() {
        return new CreateCourier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                null);
    }

    public static CreateCourier getNewCourierWithLoginNull() {
        return new CreateCourier(null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier getNewCourierWithPasswordNull() {
        return new CreateCourier(RandomStringUtils.randomAlphabetic(10),
                null,
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier delete(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);

        return spec()
                .body(json)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }

    private static RequestSpecification spec()
    {
            return given().log().all()
                    .contentType(ContentType.JSON)
                    .baseUri(BASE_URI)
                    .basePath(API_PREFIX)
                    ;
        }

    public void deleteCourier() {
        if (courierId > 0) {
            CreateCourier response = GeneratingCourier.delete(courierId);

            check.deletedSuccessfully(response);
        }
    }
}
}
//