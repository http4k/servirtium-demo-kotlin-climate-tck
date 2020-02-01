package servirtium.http4k.kotlin

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.has
import com.natpryce.hamkrest.throws
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import servirtium.http4k.ClimateApi
import servirtium.http4k.ClimateApi.BadDateRange
import servirtium.http4k.ClimateApi.CountryISOwrong

interface ClimateApiTests {
    val uri: Uri

    @Test
    @JvmDefault
    fun averageRainfallForGreatBritainFrom1980To1999Exists() {
        assertThat(ClimateApi(uri).getAveAnnualRainfall(1980, 1999, "gbr"), equalTo(988.8454972331015))
    }

    @Test
    @JvmDefault
    fun averageRainfallForFranceFrom1980To1999Exists() {
        assertThat(ClimateApi(uri).getAveAnnualRainfall(1980, 1999, "fra"), equalTo(913.7986955122727))
    }

    @Test
    @JvmDefault
    fun averageRainfallForEgyptFrom1980To1999Exists() {
        assertThat(ClimateApi(uri).getAveAnnualRainfall(1980, 1999, "egy"), equalTo(54.58587712129825))
    }

    @Test
    @JvmDefault
    fun averageRainfallForGreatBritainFrom1985To1995DoesNotExist() {
        assertThat({ ClimateApi(uri).getAveAnnualRainfall(1985, 1995, "gbr") }, throws(
            has(BadDateRange::getLocalizedMessage, equalTo("date range 1985-1995 not supported"))
        ))
    }

    @Test
    @JvmDefault
    fun averageRainfallForMiddleEarthFrom1985To1995DoesNotExist() {
        assertThat({ ClimateApi(uri).getAveAnnualRainfall(1980, 1999, "mde") }, throws(
            has(CountryISOwrong::getLocalizedMessage, equalTo("mde not recognized by climateweb"))
        ))
    }

    @Test
    @JvmDefault
    fun averageRainfallForGreatBritainAndFranceFrom1980To1999CanBeCalculatedFromTwoRequests() {
        assertThat(ClimateApi(uri).getAveAnnualRainfall(1980, 1999, "gbr", "fra"), equalTo(951.3220963726872))
    }
}