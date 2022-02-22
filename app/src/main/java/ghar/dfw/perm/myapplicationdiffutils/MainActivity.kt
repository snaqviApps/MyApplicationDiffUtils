package ghar.dfw.perm.myapplicationdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

//    private lateinit var mainActivityAdapter: SampleListAdapter
    private lateinit var mainWeatherAdapter: WeatherInfoAdapter
    private lateinit var weatherComponent: DiffsViewModel
    private lateinit var diffUtilViewModel: DiffsViewModel
    private lateinit var binding: ActivityMainBinding

    private val list01 = listOf(
        SampleListItem(1, "h10", "h100"),
        SampleListItem(2, "h20", "h200"),
        SampleListItem(3, "h30", "h300"),
        SampleListItem(4, "h40", "h400"))

    private val list12 : List<SampleListItem> = listOf(
        SampleListItem(4, "h40", "julie"),
        SampleListItem(5, "h50", "hanson"),
        SampleListItem(6, "h60", "mashto"))

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

//        mainActivityAdapter = SampleListAdapter()
//        binding.sampleList.adapter = mainActivityAdapter
//        mainActivityAdapter.submitList(
//                listOf(list01.get(0), list01.get(1), list01.get(2), list01.get(3))
//            )
//        mainWeatherAdapter.submitList()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = mainActivityAdapter                    // using other approach for ViewModel Instance
        recyclerView.adapter = mainWeatherAdapter

        weatherComponent = DaggerWeatherGraph.builder().build().getWeatherData()
        weatherComponent.getWeatherInfo().observe(this, Observer { it: Response<WeatherInfo> ->
            Toast.makeText(baseContext, "weather-Info-daggered: ${it.body()}", Toast.LENGTH_LONG).show()
            mainWeatherAdapter.submitList(listOf(it.body()))
        })


    }
}

