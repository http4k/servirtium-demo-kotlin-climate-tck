package servirtium.http4k.java;

import org.http4k.core.Uri;
import servirtium.http4k.ClimateApi;

public class DirectClimateApiTests implements ClimateApiTests {

    @Override
    public Uri uri() {
        return ClimateApi.DEFAULT_CLIMATE_API_SITE;
    }
}
