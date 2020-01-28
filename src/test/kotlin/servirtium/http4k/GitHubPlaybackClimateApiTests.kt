package servirtium.http4k

import org.http4k.core.Credentials
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.server.Http4kServer
import org.http4k.servirtium.Github
import org.http4k.servirtium.InteractionOptions
import org.http4k.servirtium.InteractionStorage
import org.http4k.servirtium.ServirtiumServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import java.nio.file.Path

class GitHubPlaybackClimateApiTests : ClimateApiTests {
    override val uri by lazy { Uri.of("http://localhost:${servirtium.port()}") }

    private lateinit var servirtium: Http4kServer

    @BeforeEach
    fun start(info: TestInfo) {
        servirtium = ServirtiumServer.Replay(info.displayName.removeSuffix("()"),
            InteractionStorage.Github("http4k", "servirtium-demo-kotlin-climate-tck",
                Credentials("<github user>", "<personal access token>"),
                Path.of("src/test/resources")
            ),
            object : InteractionOptions {
                override fun modify(request: Request) = request.header("Date", "some overridden date")
            }
        )
        servirtium.start()
    }

    @AfterEach
    fun stop() {
        servirtium.stop()
    }
}