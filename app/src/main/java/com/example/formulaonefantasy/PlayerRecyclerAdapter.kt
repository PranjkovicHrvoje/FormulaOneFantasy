package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerRecyclerAdapter(
    val items: ArrayList<Player>

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

    fun addItem(player: Player){
        items.add(player)
        notifyItemInserted(items.size)
    }


    class PlayerViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val player_Name = itemView.findViewById<TextView>(R.id.player_standings_nickname)
        private val player_Favorite = itemView.findViewById<TextView>(R.id.player_profile_favorite_driver)
        private val player_Points = itemView.findViewById<TextView>(R.id.player_standings_points)

        fun bind(player: Player){
            player_Name.text = player.nickname
            player_Favorite.text = player.favorite
            player_Points.text = player.points.toString()
        }
    }

}