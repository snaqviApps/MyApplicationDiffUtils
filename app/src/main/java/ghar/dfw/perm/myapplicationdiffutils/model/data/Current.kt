package ghar.dfw.perm.myapplicationdiffutils.model.data


import com.google.gson.annotations.SerializedName
import javax.inject.Inject


data class Current @Inject constructor(
    @SerializedName("air_quality")
    val airQuality: AirQuality,
    @SerializedName("cloud")
    val cloud: Int,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_mph")
    val windMph: Double
)