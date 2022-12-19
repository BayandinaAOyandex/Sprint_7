package generatingOfClasses;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreateCourier;

public class GeneratingCourier {
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
}
