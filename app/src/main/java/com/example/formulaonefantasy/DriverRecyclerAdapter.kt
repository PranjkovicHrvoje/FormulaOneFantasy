package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DriverRecyclerAdapter(private val items: ArrayList<Drivers>):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DriverViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.driver_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DriverViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class DriverViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val driverImage = itemView.findViewById<ImageView>(R.id.driver_photo)
        private val driverName = itemView.findViewById<TextView>(R.id.driver_name)
        private val driverTeam = itemView.findViewById<TextView>(R.id.driver_team)
        private val driverCountry = itemView.findViewById<TextView>(R.id.driver_country)

        fun bind(driver: Drivers){
            Glide.with(itemView.context).load(driver.image).into(driverImage)
            driverName.text = driver.name
            driverTeam.text = driver.team
            driverCountry.text = driver.country
        }
    }
}