package servirtium.http4k.java;

import org.http4k.client.JavaHttpClient;
import org.http4k.core.Credentials;
import org.http4k.core.Http4kKt;
import org.http4k.core.Uri;
import org.http4k.filter.ClientFilters;
import org.http4k.servirtium.Github;
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
        return Uri.Companion.of("http://localhost:" + servirtium.port());
    }

    private ServirtiumServer servirtium;

    @BeforeEach
    public void start(TestInfo info) {
        servirtium = ServirtiumServer.Replay(
                getMarkdownNameFrom(info),
                new Github("http4k", "servirtium-demo-kotlin-climate-tck",
                        new Credentials("<github user>", "<personal access token>"),
                        Paths.get("src/test/resources"),
                        "master",
                        Http4kKt.then(ClientFilters.SetBaseUriFrom.INSTANCE.invoke(Uri.Companion.of("https://api.github.com")), JavaHttpClient.INSTANCE.invoke())),
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
