package com.example.albumtracker.ui.search

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.example.albumtracker.R
import com.example.albumtracker.network.ApiService
import com.example.albumtracker.network.PlayerResponse
import com.example.albumtracker.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

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

        val txtName =
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
            val playerName = etPlayer.text.toString()
            if (playerName.isBlank()) return@setOnClickListener

            lifecycleScope.launch {
                try {
                    val response = RetrofitClient.api.searchPlayer(playerName)
                    if (response.isSuccessful) {
                        val player = response.body()?.player?.firstOrNull()
                        if (player != null) {
                            txtName.text = player.strPlayer
                            txtNationality.text = player.strNationality
                            txtTeam.text = player.strTeam
                            txtPosition.text = player.strPosition

                            Glide.with(this@SearchPlayerActivity)
                                .load(player.strThumb)
                                .into(imgPlayer)
                        } else {
                            Toast.makeText(
                                this@SearchPlayerActivity,
                                "Jugador no encontrado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@SearchPlayerActivity,
                            "Error en la respuesta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@SearchPlayerActivity,
                        "Error al buscar: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}