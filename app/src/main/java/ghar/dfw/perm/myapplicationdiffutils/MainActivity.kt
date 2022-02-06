package ghar.dfw.perm.myapplicationdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.pure.utils.Classes
import ghar.dfw.perm.myapplicationdiffutils.pure.utils.Util
import ghar.dfw.perm.myapplicationdiffutils.view.DiffsViewModel
import ghar.dfw.perm.myapplicationdiffutils.view.SampleListAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {

    private lateinit var diffUtilViewModel: DiffsViewModel

    private val list01 = listOf(
        SampleListItem(1, "h10", "h100"),
        SampleListItem(2, "h20", "h200"),
        SampleListItem(3, "h30", "h300"),
        SampleListItem(4, "h40", "h400"))

    private val list12 : List<SampleListItem> = listOf(
        SampleListItem(4, "h40", "h400"),
        SampleListItem(5, "h50", "h500"),
        SampleListItem(6, "h60", "h600"))

    override fun onCreate(savedInstanceState: Bundle?) {

        diffUtilViewModel = ViewModelProvider(this).get(DiffsViewModel::class.java)

        /** ---------------------- Utils ---------------------------- **/
        /** --------------------------------------------------------- **/
        val util = Util()
        val accumList = util.getCompositeList(list01, list12)
        for(item in accumList){
            println("accumulated list: $item")
        }
        val employees = listOf(
            Classes("JJ", "Black", 2006),
            Classes("LM", "White", 2009),
            Classes("LM1", "White1", 2019)
        )
        /** --------------------------------------------------------- **/
        /** ---------------------Utils END HERE  -------------------- **/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView  = findViewById<RecyclerView>(R.id.sampleList)
        val adapter = SampleListAdapter()
        adapter.submitList(
                listOf(list01.get(0), list01.get(1), list01.get(2), list01.get(3))
            )
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

        /** call network using retrofit from View, for demo-purpose only */
        GlobalScope.launch() {
            diffUtilViewModel.networkCall()
          }

        diffUtilViewModel.weatherResponse.observe(this, Observer{
            Toast.makeText(baseContext, "weather-Info: ${it.body()}", Toast.LENGTH_LONG).show()
        })
    }
}

