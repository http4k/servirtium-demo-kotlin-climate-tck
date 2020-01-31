package servirtium.http4k.kotlin

import servirtium.http4k.ClimateApi.Companion.DEFAULT_CLIMATE_API_SITE

class DirectClimateApiTests : ClimateApiTests {
    override val uri = DEFAULT_CLIMATE_API_SITE
}

