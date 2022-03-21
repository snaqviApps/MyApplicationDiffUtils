package ghar.dfw.perm.myapplicationdiffutils.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.SampleListItem
import ghar.dfw.perm.myapplicationdiffutils.R

class SampleListAdapter : ListAdapter<SampleListItem, SampleListAdapter.SampleListViewHolder>
    (SampleDiffUtil()) {

    val weatherInfo = DiffsViewModel().getWeatherInfo().value?.body()            // get Data
    val location = weatherInfo?.current
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return SampleListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SampleListViewHolder, position: Int) {
        var sampleItem = getItem(position)
        holder.bind(sampleItem, this.location?.condition?.text)
//        holder.bind(movieItem)

//        holder.name.text = movieItem.backdrop_path
    }

    class SampleListViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.tvName)
        val defaultValue = view.findViewById<TextView>(R.id.tvDefault)

        fun bind(sampleItem: SampleListItem, str:String?){
//           name.text = sampleItem.name
           name.text = str ?: "looks like no weather-info received"
           defaultValue.text = sampleItem.default
        }
    }

}

class SampleDiffUtil : DiffUtil.ItemCallback<SampleListItem>() {
    override fun areItemsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean {
        return oldItem.id == newItem.id     // item-entry
    }

    override fun areContentsTheSame(oldItem: SampleListItem, newItem: SampleListItem): Boolean {
        return oldItem == newItem           // properties (entries) of data class
    }

}