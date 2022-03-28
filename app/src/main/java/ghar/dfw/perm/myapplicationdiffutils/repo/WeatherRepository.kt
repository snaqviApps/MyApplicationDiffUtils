package ghar.dfw.perm.myapplicationdiffutils.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import ghar.dfw.perm.myapplicationdiffutils.BuildConfig
import ghar.dfw.perm.myapplicationdiffutils.api.WeatherApi
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


//class WeatherRepository

@Module
class WeatherRepository @Inject constructor()
{
    private val _weatherResponse = MutableLiveData<Response<WeatherInfo>>()
    val weatherResponse: LiveData<Response<WeatherInfo>>
        get() = _weatherResponse

    suspend fun networkCall() {
        withContext(Dispatchers.IO) {
            try {
                _weatherResponse.postValue(
                    WeatherApi.weatherApiService.getWeatherData(
                        BuildConfig.WEATHER_API_KEY,
                        location = "Dallas",
                        aqiChoice = "yes"
                    )
                )
                println("weather-data-in network call: ${_weatherResponse.value?.body()}")
            } catch (exception: Exception) {
                Timber.e("error getting movies-data from server: ${exception.printStackTrace()}")
                exception.printStackTrace()
            }
        }
    }

    @Provides
    suspend fun injectsWeatherRepo()  = networkCall()

}


