package servirtium.http4k.java;

import org.http4k.core.Uri;
import org.http4k.server.SunHttp;
import org.http4k.servirtium.ServirtiumServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.File;

import static org.http4k.servirtium.InteractionStorage.Disk;
import static servirtium.http4k.java.JUnitUtil.getMarkdownNameFrom;

public class DiskReplayClimateApiTests implements ClimateApiTests {

    @Override
    public Uri uri() {
        return Uri.of("http://localhost:" + servirtium.port());
    }

    private ServirtiumServer servirtium;

    @BeforeEach
    public void start(TestInfo info) {
        servirtium = ServirtiumServer.Replay(
                getMarkdownNameFrom(info),
                Disk(new File("src/test/resources")),
                new ClimateInteractionOptions(), 0, SunHttp::new
        );
        servirtium.start();
    }

    @AfterEach
    public void stop() {
        servirtium.stop();
    }
}
