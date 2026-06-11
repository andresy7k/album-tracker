package com.example.albumtracker.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.model.Sticker

class StickerAdapter(
    private val stickers: List<Sticker>
) : RecyclerView.Adapter<StickerAdapter.StickerViewHolder>() {

    class StickerViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val txtNumber: TextView =
            itemView.findViewById(R.id.txtStickerNumber)

        val txtPlayer: TextView =
            itemView.findViewById(R.id.txtPlayerName)

        val txtCountry: TextView =
            itemView.findViewById(R.id.txtCountry)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStickerStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickerViewHolder {

        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_sticker,
            parent,
            false
        )

        return StickerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: StickerViewHolder,
        position: Int
    ) {

        val sticker = stickers[position]

        holder.txtNumber.text =
            "#${sticker.number}"

        holder.txtPlayer.text =
            sticker.playerName

        holder.txtCountry.text =
            sticker.country

        holder.txtStatus.text =
            if (sticker.obtained)
                "Obtained"
            else
                "Missing"
    }

    override fun getItemCount(): Int {
        return stickers.size
    }
}