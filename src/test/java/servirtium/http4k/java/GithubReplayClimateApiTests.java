package servirtium.http4k.java;

import org.http4k.core.Credentials;
import org.http4k.core.Uri;
import org.http4k.server.SunHttp;
import org.http4k.servirtium.GitHub;
import org.http4k.servirtium.ServirtiumServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInfo;

import java.nio.file.Paths;

import static servirtium.http4k.java.JUnitUtil.getMarkdownNameFrom;

/**
 * To run this test, remove the disabled annotation and insert a valid github user and
 * personal access token.
 */
@Disabled
public class GithubReplayClimateApiTests implements ClimateApiTests {

    @Override
    public Uri uri() {
        return Uri.of("http://localhost:" + servirtium.port());
    }

    private ServirtiumServer servirtium;

    @BeforeEach
    public void start(TestInfo info) {
        servirtium = ServirtiumServer.Replay(
                getMarkdownNameFrom(info),
                new GitHub("http4k", "servirtium-demo-kotlin-climate-tck",
                        new Credentials("<github user>", "<personal access token>"),
                        Paths.get("src/test/resources"),
                        "master"),
                new ClimateInteractionOptions(), 0, SunHttp::new
        );
        servirtium.start();
    }

    @AfterEach
    public void stop() {
        servirtium.stop();
    }
}
