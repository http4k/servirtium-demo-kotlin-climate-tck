package servirtium.http4k.java;

import org.http4k.core.Uri;
import org.junit.jupiter.api.Test;
import servirtium.http4k.ClimateApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface ClimateApiTests {

    Uri uri();

    @Test
    default void averageRainfallForGreatBritainFrom1980To1999Exists() {
        assertEquals(new ClimateApi(uri()).getAveAnnualRainfall(1980, 1999, "gbr"), 988.8454972331015);
    }
}
