package servirtium.http4k.java;

import org.http4k.core.Uri;
import org.http4k.servirtium.InteractionStorage;
import org.http4k.servirtium.ServirtiumServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import servirtium.http4k.ClimateApi;
import servirtium.http4k.kotlin.ClimateInteractionOptions;

import java.io.File;

public class DiskRecordingClimateApiTests implements ClimateApiTests {

    @Override
    public Uri uri() {
        return Uri.Companion.of("http://localhost:" + servirtium.port());
    }

    private ServirtiumServer servirtium;

    @BeforeEach
    public void start(TestInfo info) {
        servirtium = ServirtiumServer.Companion.Recording(
                info.getDisplayName().substring(0, info.getDisplayName().indexOf('(')),
                ClimateApi.DEFAULT_CLIMATE_API_SITE,
                InteractionStorage.Companion.Disk(new File("src/test/resources")),
                ClimateInteractionOptions.INSTANCE,
                0
        );
        servirtium.start();
    }

    @AfterEach
    public void stop() {
        servirtium.stop();
    }
}
