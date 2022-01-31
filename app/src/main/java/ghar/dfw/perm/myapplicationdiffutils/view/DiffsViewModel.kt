package ghar.dfw.perm.myapplicationdiffutils.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ghar.dfw.perm.myapplicationdiffutils.BuildConfig
import ghar.dfw.perm.myapplicationdiffutils.api.TMDBApi
import ghar.dfw.perm.myapplicationdiffutils.model.data.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

class DiffsViewModel:ViewModel() {

    private val _moviesResponse = MutableLiveData<Response<Movies>>()
    val moviesResponse : LiveData<Response<Movies>>
        get() = _moviesResponse

    suspend fun networkCall() {
        withContext(Dispatchers.IO) {
            try {
                val result = TMDBApi.tmdbApiService.getMovies(api_key = BuildConfig.MOVIES_API_KEY)
                _moviesResponse.value = TMDBApi.tmdbApiService.getMovies(api_key = BuildConfig.MOVIES_API_KEY)
//                Timber.i("original data: ${moviesResponse?.body()?.original_title}")
                result.body()?.production_companies?.forEach {
                    Timber.i("movies-data in call: $it")
                }

            } catch (exception: Exception) {
                Timber.e("error getting asteroid-data from server: ${exception.printStackTrace()}")
                exception.printStackTrace()
            }
        }

    }


}