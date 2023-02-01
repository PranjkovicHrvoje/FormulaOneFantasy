package com.example.formulaonefantasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrentUserRecyclerAdapter(
    private val items: ArrayList<CurrentUser>

):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrentUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_profile, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CurrentUserViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(currentUser: CurrentUser){
        items.add(currentUser)
        notifyItemInserted(items.size)
    }

    class CurrentUserViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        private val playerName = itemView.findViewById<TextView>(R.id.player_profile_nickname)
        private val playerPoints = itemView.findViewById<TextView>(R.id.player_profile_points)
        private val playerFavorite = itemView.findViewById<TextView>(R.id.player_profile_favorite_driver)

        fun bind(currentUser: CurrentUser){
            playerName.text = currentUser.nickname
            playerPoints.text = currentUser.points.toString()
            playerFavorite.text = currentUser.favorite
        }
    }

}