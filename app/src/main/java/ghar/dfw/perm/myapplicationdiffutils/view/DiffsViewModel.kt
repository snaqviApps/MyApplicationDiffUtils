package ghar.dfw.perm.myapplicationdiffutils.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import ghar.dfw.perm.myapplicationdiffutils.di.DaggerWeatherGraph
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@Module
class DiffsViewModel @Inject constructor() :  ViewModel()
{
    private val weatherRepo = DaggerWeatherGraph.builder().build().providedWeatherRepo()
    init {
        viewModelScope.launch {
           weatherRepo.injectsWeatherRepo()
        }
    }

    @Provides
    fun getWeatherInfo(): LiveData<Response<WeatherInfo>> {
        return weatherRepo.weatherResponse
    }
}
