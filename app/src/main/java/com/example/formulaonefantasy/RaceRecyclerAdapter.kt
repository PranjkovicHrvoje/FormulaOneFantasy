package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RaceRecyclerAdapter(val items: ArrayList<Race>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.season_schedule, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RaceViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RaceViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val raceName = itemView.findViewById<TextView>(R.id.event_name)
        private val trackName = itemView.findViewById<TextView>(R.id.circuit_name)
        private val raceDate = itemView.findViewById<TextView>(R.id.race_date)

        fun bind(race: Race){
            raceName.text = race.name
            trackName.text = race.track
            raceDate.text = race.date
        }
    }
}