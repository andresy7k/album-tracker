package com.example.albumtracker.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.model.Sticker

class StickerAdapter(
    private var stickers: MutableList<Sticker>,
    private val onObtained: (Sticker) -> Unit,
    private val onRepeated: (Sticker) -> Unit
) : RecyclerView.Adapter<StickerAdapter.StickerViewHolder>() {

    class StickerViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val txtNumber: TextView =
            itemView.findViewById(R.id.txtNumber)

        val txtPlayer: TextView =
            itemView.findViewById(R.id.txtPlayer)

        val txtCountry: TextView =
            itemView.findViewById(R.id.txtCountry)

        val txtStatus: TextView =
            itemView.findViewById(R.id.txtStatus)

        val txtRepeated: TextView =
            itemView.findViewById(R.id.txtRepeated)

        val btnObtained: Button =
            itemView.findViewById(R.id.btnObtained)

        val btnRepeat: Button =
            itemView.findViewById(R.id.btnRepeat)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StickerViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_sticker,
                    parent,
                    false
                )

        return StickerViewHolder(view)
    }

    override fun getItemCount() =
        stickers.size

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
            if(sticker.obtained)
                "Obtenida"
            else
                "Pendiente"

        holder.txtRepeated.text =
            "Repetidas: ${sticker.repeatedCount}"

        holder.btnObtained.setOnClickListener {
            onObtained(sticker)
        }

        holder.btnRepeat.setOnClickListener {
            onRepeated(sticker)
        }
    }

    fun updateData(
        newList: MutableList<Sticker>
    ) {

        stickers = newList

        notifyDataSetChanged()
    }
}