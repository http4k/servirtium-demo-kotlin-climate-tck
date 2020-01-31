package servirtium.http4k.kotlin

import org.http4k.core.Uri
import org.http4k.servirtium.InteractionStorage.Companion.Disk
import org.http4k.servirtium.ServirtiumServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import servirtium.http4k.ClimateApi.Companion.DEFAULT_CLIMATE_API_SITE
import java.io.File

class DiskRecordingClimateApiTest : ClimateApiTests {

    override val uri by lazy { Uri.of("http://localhost:${servirtium.port()}") }

    private lateinit var servirtium: ServirtiumServer

    @BeforeEach
    fun start(info: TestInfo) {
        servirtium = ServirtiumServer.Recording(
            info.markdownName(),
            DEFAULT_CLIMATE_API_SITE,
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

