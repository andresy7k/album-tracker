package com.example.albumtracker.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.model.Sticker

class DashboardAdapter(
    private val stickers: List<Sticker>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val txtNumber: TextView =
            itemView.findViewById(R.id.txtNumber)

        val txtPlayer: TextView =
            itemView.findViewById(R.id.txtPlayer)

        val txtCountry: TextView =
            itemView.findViewById(R.id.txtCountry)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_recent_sticker,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun getItemCount() =
        stickers.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val sticker = stickers[position]

        holder.txtNumber.text =
            "#${sticker.number}"

        holder.txtPlayer.text =
            sticker.playerName

        holder.txtCountry.text =
            sticker.country
    }
}