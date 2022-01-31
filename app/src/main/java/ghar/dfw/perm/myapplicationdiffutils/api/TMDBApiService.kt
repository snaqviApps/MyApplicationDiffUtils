package ghar.dfw.perm.myapplicationdiffutils.api

import ghar.dfw.perm.myapplicationdiffutils.model.data.Movies
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Sample Api details:
 20220127204451
 https://api.themoviedb.org/3/movie/550?api_key=fcb4ae381c4482341fc74a85ea0b071a
*/


const val API_KEY = "fcb4ae381c4482341fc74a85ea0b071a"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val END_POINT = "movies/550"

interface TMDBApiService {
    @GET(END_POINT)
    suspend fun getMovies(
        @Query(API_KEY) api_key:String
    ):Response<Movies>
}

// Retrofit-Object
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.MILLISECONDS)
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object TMDBApi {
    val tmdbApiService: TMDBApiService by lazy {
        retrofit.create(TMDBApiService::class.java)
    }
}