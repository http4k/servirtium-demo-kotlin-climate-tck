package servirtium.http4k

import com.thoughtworks.xstream.XStream
import org.http4k.client.JavaHttpClient
import org.http4k.client.OkHttp
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters.SetBaseUriFrom
import org.http4k.filter.DebuggingFilters

class ClimateApi(baseUri: Uri) {
    private val xStream = XStream().apply {
        alias("domain.web.AnnualGcmDatum", AnnualGcmDatum::class.java)
        aliasField("double", AnnualData::class.java, "doubleVal")
    }

    private val http = SetBaseUriFrom(baseUri).then(JavaHttpClient())

    fun getAveAnnualRainfall(fromCCYY: Int, toCCYY: Int, vararg countryISOs: String): Double {
        val total = countryISOs.map { countryISO ->
            val response = http(Request(GET, "/climateweb/rest/v1/country/annualavg/pr/$fromCCYY/$toCCYY/$countryISO.xml"))

            val xml = response.bodyString()

            // non-existent country returns a simple message string (with the wrong mime
            // type, as it happens.
            if (xml.contains("Invalid country code. Three letters are required"))
                throw CountryISOwrong("$countryISO not recognized by climateweb")

            @Suppress("UNCHECKED_CAST")
            val agd = xStream.fromXML(xml) as List<AnnualGcmDatum>

            // wrong date ranges just return an empty list of data - a choice of
            // WorldBank's Climate API.
            if (agd.isEmpty()) throw BadDateRange("date range $fromCCYY-$toCCYY not supported")

            val sum = agd.map { it.annualData.doubleVal }.sum()

            sum / agd.size
        }.sum()

        // Average of N averages?  OK, look past that!
        return total / countryISOs.size
    }

    companion object {
        @JvmField
        val DEFAULT_CLIMATE_API_SITE = Uri.of("http://climatedataapi.worldbank.org")
    }

    class BadDateRange(message: String?) : UnsupportedOperationException(message)
    class CountryISOwrong(message: String?) : UnsupportedOperationException(message)

}
