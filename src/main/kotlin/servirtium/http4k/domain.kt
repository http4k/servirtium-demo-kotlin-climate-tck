package servirtium.http4k

data class AnnualData(val doubleVal: Double)

data class AnnualGcmDatum(
    var gcm: String,
    var variable: String,
    var fromYear: Int,
    var toYear: Int,
    val annualData: AnnualData
)