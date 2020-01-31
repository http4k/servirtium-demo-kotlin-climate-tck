package servirtium.http4k.kotlin

import org.http4k.core.Credentials
import org.http4k.core.Uri
import org.http4k.server.Http4kServer
import org.http4k.servirtium.Github
import org.http4k.servirtium.ServirtiumServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.TestInfo
import java.nio.file.Paths

/**
 * To run this test, remove the disabled annotation and insert a valid github user and
 * personal access token.
 */
@Disabled
class GitHubPlaybackClimateApiTests : ClimateApiTests {
    override val uri by lazy { Uri.of("http://localhost:${servirtium.port()}") }

    private lateinit var servirtium: Http4kServer

    @BeforeEach
    fun start(info: TestInfo) {
        servirtium = ServirtiumServer.Replay(info.displayName.removeSuffix("()"),
            Github("http4k", "servirtium-demo-kotlin-climate-tck",
                Credentials("<github user>", "<personal access token>"),
                Paths.get("src/test/resources")
            ),
            ClimateInteractionOptions
        )
        servirtium.start()
    }

    @AfterEach
    fun stop() {
        servirtium.stop()
    }
}