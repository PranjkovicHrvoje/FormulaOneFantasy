package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DriverRecyclerAdapter(val items: ArrayList<Drivers>):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.driver_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PersonViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PersonViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val driverImage = itemView.findViewById<ImageView>(R.id.driverPhoto)
        private val driverName = itemView.findViewById<TextView>(R.id.driverName)
        private val driverBirthday = itemView.findViewById<TextView>(R.id.driverBirthday)
        private val driverCountry = itemView.findViewById<TextView>(R.id.driverCountry)

        fun bind(driver: Drivers){
            driverName.text = "${driver.GivenName} ${driver.FamilyName}"
            driverBirthday.text = driver.DateOfBirth
            driverCountry.text = driver.Nationality
        }
    }
}