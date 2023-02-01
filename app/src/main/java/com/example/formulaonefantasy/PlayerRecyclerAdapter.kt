package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerRecyclerAdapter(
    private val items: ArrayList<Players>

    ):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_standings, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PlayerViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(players: Players){
        items.add(players)
        notifyItemInserted(items.size)
    }

    class PlayerViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val playerName = itemView.findViewById<TextView>(R.id.player_standings_nickname)
        private val playerPoints = itemView.findViewById<TextView>(R.id.player_standings_points)

        fun bind(players: Players){
            playerName.text = players.nickname
            playerPoints.text = players.points.toString()
        }
    }

}