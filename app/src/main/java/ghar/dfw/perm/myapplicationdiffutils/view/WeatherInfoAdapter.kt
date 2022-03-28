package ghar.dfw.perm.myapplicationdiffutils.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ghar.dfw.perm.myapplicationdiffutils.R
import ghar.dfw.perm.myapplicationdiffutils.model.data.WeatherInfo

class WeatherInfoAdapter : ListAdapter<WeatherInfo, WeatherInfoAdapter.WeatherInfoViewHolder>
    (WeatherListDiffUtil())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return WeatherInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherInfoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class WeatherInfoViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val name = view.findViewById<TextView>(R.id.tvName)
        val countryName = view.findViewById<TextView>(R.id.tvDefault)

        fun bind(item: WeatherInfo){
            name.text = item.location.name
            countryName.text = item.location.country
        }
    }

    class WeatherListDiffUtil : DiffUtil.ItemCallback<WeatherInfo>(){
        override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem.current.cloud == newItem.current.cloud
        }

        override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }
    }

}