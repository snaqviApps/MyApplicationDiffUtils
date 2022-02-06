package ghar.dfw.perm.myapplicationdiffutils.view


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.dfw.perm.myapplicationdiffutils.BuildConfig
import ghar.dfw.perm.myapplicationdiffutils.api.WeatherApi
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class DiffsViewModel : ViewModel() {

    private val _weatherResponse = MutableLiveData<Response<WeatherInfo>>()
    val weatherResponse: LiveData<Response<WeatherInfo>>
        get() = _weatherResponse

    init {
        viewModelScope.launch {
            networkCall()
        }
    }
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
                Log.v("weather-data:", "${_weatherResponse.value?.body()}")
            } catch (exception: Exception) {
                Timber.e("error getting movies-data from server: ${exception.printStackTrace()}")
                exception.printStackTrace()
            }
        }
    }
}
