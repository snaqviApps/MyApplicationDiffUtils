package ghar.dfw.perm.myapplicationdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.pure.utils.Classes
import ghar.dfw.perm.myapplicationdiffutils.pure.utils.Util
import ghar.dfw.perm.myapplicationdiffutils.view.DiffsViewModel
import ghar.dfw.perm.myapplicationdiffutils.view.SampleListAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.*

class MainActivity : AppCompatActivity() {

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

        /** ---------------------- Utils ---------------------------- **/
        /** --------------------------------------------------------- **/
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

        // Collections
//        println(employees.minByOrNull ({ e -> e.startYear }))
//        println(employees.minByOrNull ({ e:Classes -> e.startYear }))   // same as above
//        println(employees.minByOrNull { it.startYear })   // same as above

        iterateClasses(employees, 3)
        println(count100With())
        println(count10Apply())

        /** --------------------------------------------------------- **/
        /** --------------------------------------------------------- **/
        /** ---------------------Utils END HERE  -------------------- **/

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView  = findViewById<RecyclerView>(R.id.sampleList)
        val adapter = SampleListAdapter()

        adapter.submitList(
            listOf(list01.get(0), list01.get(1), list01.get(2), list01.get(3))
        )
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter

//        Handler(Looper.getMainLooper()).postDelayed(
//            Runnable {
//                adapter.submitList(list12)
//        }, 2000)

        println("using Elvis Operator: ")
//        list12.getOrNull(6) ?: throw IllegalAccessException("IllegalAccessException: OutOfBound index")
        println(list12.getOrNull(3) ?: list01[0])

        // retrofit GET call check

        diffUtilViewModel = ViewModelProvider(this).get(DiffsViewModel::class.java)
        GlobalScope.launch {
            diffUtilViewModel.networkCall()
            Timber.d("diffUtilViewModel.moviesResponse: ${diffUtilViewModel.moviesResponse.value}")
        }
        diffUtilViewModel.moviesResponse.observe(this, {
            Toast.makeText(this, "Hi data from MainObserver ${it.body().toString()}", Toast.LENGTH_SHORT)
                .show()
        })

    }

}



// iteration
fun iterateClasses(employees: List<Classes>, num:Int) {
    employees.forEach {
            e: Classes ->
        println("here is the class list: ${e}\t$num")
    }
}

fun count100With():String {
    return with(StringBuilder()) {
        for(i in 1..99){
            append(i)
            append(", ")
        }
        append(100)
        toString()
    }
}

fun count10Apply() =
    StringBuilder().apply() {
        for(i in 1..9){
            append(i)
            append(", ")
        }
        append(10)
    }.toString()
