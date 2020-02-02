package servirtium.http4k.java;

import org.http4k.core.Uri;
import org.http4k.servirtium.InteractionStorage;
import org.http4k.servirtium.ServirtiumServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.File;

import static servirtium.http4k.java.JUnitUtil.getMarkdownNameFrom;

public class DiskReplayClimateApiTests implements ClimateApiTests {

    @Override
    public Uri uri() {
        return Uri.Companion.of("http://localhost:" + servirtium.port());
    }

    private ServirtiumServer servirtium;

    @BeforeEach
    public void start(TestInfo info) {
        servirtium = ServirtiumServer.Replay(
                getMarkdownNameFrom(info),
                InteractionStorage.Companion.Disk(new File("src/test/resources")),
                new ClimateInteractionOptions(),
                0
        );
        servirtium.start();
    }

    @AfterEach
    public void stop() {
        servirtium.stop();
    }
}
