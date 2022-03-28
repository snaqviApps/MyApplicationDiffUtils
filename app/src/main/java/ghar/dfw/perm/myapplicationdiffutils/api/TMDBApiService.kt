package ghar.dfw.perm.myapplicationdiffutils.api


import dagger.Module
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

/**
 * Movie Api details:
 20220127204451
 https://api.themoviedb.org/3/movie/550?api_key=fcb4ae381c4482341fc74a85ea0b071a
*/
/** Weather Api details
 * / https://api.weatherapi.com/v1/current.json?key=7e5058209ebd4cda9eb65056220602&q=London&aqi=no
 *
 * https://api.weatherapi.com/v1/current.jsonq=Dallas?key=7e5058209ebd4cda9eb65056220602&q=Dallas}
 */

const val WEATHER_BASE_URL = "https://api.weatherapi.com/"
const val WEATHER_END_POINT = "/v1/current.json"

interface WeatherApiService {
    @GET(WEATHER_END_POINT)
    suspend fun getWeatherData(
        @Query("key") weather_api_key: String,
        @Query("q") location : String,
        @Query("aqi") aqiChoice : String
    ):Response<WeatherInfo>
}

val wRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

object WeatherApi {
    val weatherApiService: WeatherApiService by lazy {
        wRetrofit.create(WeatherApiService::class.java)
    }
}