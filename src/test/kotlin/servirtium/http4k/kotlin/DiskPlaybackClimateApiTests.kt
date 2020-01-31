package servirtium.http4k.kotlin

import org.http4k.core.Uri
import org.http4k.server.Http4kServer
import org.http4k.servirtium.InteractionStorage.Companion.Disk
import org.http4k.servirtium.ServirtiumServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import java.io.File

class DiskPlaybackClimateApiTests : ClimateApiTests {
    override val uri by lazy { Uri.of("http://localhost:${servirtium.port()}") }

    private lateinit var servirtium: Http4kServer

    @BeforeEach
    fun start(info: TestInfo) {
        servirtium = ServirtiumServer.Replay(info.displayName.removeSuffix("()"),
            Disk(File("src/test/resources")),
            ClimateInteractionOptions
        )
        servirtium.start()
    }

    @AfterEach
    fun stop() {
        servirtium.stop()
    }
}