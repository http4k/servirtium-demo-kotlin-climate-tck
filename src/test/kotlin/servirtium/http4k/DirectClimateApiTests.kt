package servirtium.http4k.servirtium.http4k

import servirtium.http4k.ClimateApi.Companion.DEFAULT_CLIMATE_API_SITE
import servirtium.http4k.ClimateApiTests

class DirectClimateApiTests : ClimateApiTests {
    override val uri = DEFAULT_CLIMATE_API_SITE
}

