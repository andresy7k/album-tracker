package com.example.albumtracker.ui.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.albumtracker.R
import com.example.albumtracker.network.RetrofitClient
import kotlinx.coroutines.launch

class SearchPlayerActivity : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_search_player
        )

        val etPlayer =
            findViewById<EditText>(
                R.id.etPlayer
            )

        val btnSearch =
            findViewById<Button>(
                R.id.btnSearch
            )

        val imgPlayer =
            findViewById<ImageView>(
                R.id.imgPlayer
            )

        val txtPlayerName =
            findViewById<TextView>(
                R.id.txtPlayerName
            )

        val txtNationality =
            findViewById<TextView>(
                R.id.txtNationality
            )

        val txtTeam =
            findViewById<TextView>(
                R.id.txtTeam
            )

        val txtPosition =
            findViewById<TextView>(
                R.id.txtPosition
            )

        btnSearch.setOnClickListener {

            val query =
                etPlayer.text.toString()

            lifecycleScope.launch {

                try {

                    val response =
                        RetrofitClient.api
                            .searchPlayer(query)

                    if(response.isSuccessful) {

                        val player =
                            response.body()
                                ?.player
                                ?.firstOrNull()

                        if(player != null) {

                            txtPlayerName.text =
                                player.strPlayer

                            txtNationality.text =
                                "País: ${player.strNationality}"

                            txtTeam.text =
                                "Equipo: ${player.strTeam}"

                            txtPosition.text =
                                "Posición: ${player.strPosition}"

                            Glide.with(
                                this@SearchPlayerActivity
                            )
                                .load(
                                    player.strThumb
                                )
                                .into(
                                    imgPlayer
                                )

                        } else {

                            txtPlayerName.text =
                                "Jugador no encontrado"

                        }
                    }

                } catch (
                    e: Exception
                ) {

                    txtPlayerName.text =
                        "Error de conexión"

                }
            }
        }
    }
}