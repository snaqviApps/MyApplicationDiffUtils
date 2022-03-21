package ghar.dfw.perm.myapplicationdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.databinding.ActivityMainBinding
import ghar.dfw.perm.myapplicationdiffutils.di.DaggerWeatherGraph
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo
import ghar.dfw.perm.myapplicationdiffutils.view.DiffsViewModel
import ghar.dfw.perm.myapplicationdiffutils.view.WeatherInfoAdapter
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    private lateinit var mainWeatherAdapter: WeatherInfoAdapter
    private lateinit var weatherComponent: DiffsViewModel
    private lateinit var diffUtilViewModel: DiffsViewModel
    private lateinit var binding: ActivityMainBinding

    /** Kotlin - Pure (language features end here */

    override fun onCreate(savedInstanceState: Bundle?) {

        diffUtilViewModel = ViewModelProvider(this).get(DiffsViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView  = findViewById<RecyclerView>(R.id.sampleList)
        mainWeatherAdapter = WeatherInfoAdapter()
        binding.sampleList.adapter = mainWeatherAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mainWeatherAdapter

        weatherComponent = DaggerWeatherGraph.builder().build().getWeatherData()
        weatherComponent.getWeatherInfo().observe(this, Observer { it: Response<WeatherInfo> ->
            mainWeatherAdapter.submitList(listOf(it.body()))
        })

    }
}

