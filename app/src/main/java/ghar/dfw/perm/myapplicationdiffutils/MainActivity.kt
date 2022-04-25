package ghar.dfw.perm.myapplicationdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.databinding.ActivityMainBinding
import ghar.dfw.perm.myapplicationdiffutils.di.DaggerWeatherGraph
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import ghar.dfw.perm.myapplicationdiffutils.view.WeatherInfoAdapter
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    private lateinit var mainWeatherAdapter: WeatherInfoAdapter
//    private lateinit var diffUtilViewModel: DiffsViewModel
    private lateinit var binding: ActivityMainBinding

    /** Kotlin - Pure (language features end here */

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val dViewModel = DaggerWeatherGraph.builder().build().provideWeatherDataViewModel()
        val mainActWeatherDataRecyclerView  = findViewById<RecyclerView>(R.id.weatherDataRecyclerView)
        mainWeatherAdapter = WeatherInfoAdapter()

        binding.bViewModel = dViewModel
        binding.lifecycleOwner = this       
        binding.weatherDataRecyclerView.adapter = mainWeatherAdapter

        mainActWeatherDataRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActWeatherDataRecyclerView.setHasFixedSize(true)
        mainActWeatherDataRecyclerView.adapter = mainWeatherAdapter

        dViewModel.getWeatherInfo().observe(this, Observer { it: Response<WeatherInfo> ->
            mainWeatherAdapter.submitList(listOf(it.body()))
        })

    }
}

