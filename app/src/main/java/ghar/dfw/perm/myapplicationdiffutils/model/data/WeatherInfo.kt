package ghar.dfw.perm.myapplicationdiffutils.model.data


import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class WeatherInfo @Inject constructor(
    @SerializedName("current")
   val current: Current,
    @SerializedName("location")
   val location: Location
){}