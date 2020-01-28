package servirtium.http4k

data class AnnualData(val doubleVal: Double = 0.0)

data class AnnualGcmDatum(
    var gcm: String? = null,
    var variable: String? = null,
    var fromYear: Int = 0,
    var toYear: Int = 0,
    val annualData: AnnualData
)