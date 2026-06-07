package com.example.albumtracker.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.albumtracker.R
import com.example.albumtracker.databinding.ActivitySearchPlayerBinding
import com.example.albumtracker.network.RetrofitClient
import kotlinx.coroutines.launch

class SearchPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val query = binding.etPlayer.text.toString().trim()
            if (query.isNotEmpty()) {
                searchPlayer(query)
            } else {
                binding.etPlayer.error = getString(R.string.error_empty_query)
            }
        }
    }

    private fun searchPlayer(query: String) {
        lifecycleScope.launch {
            setLoading(true)
            try {
                val response = RetrofitClient.api.searchPlayer(query)
                if (response.isSuccessful) {
                    val player = response.body()?.player?.firstOrNull()
                    if (player != null) {
                        displayPlayer(player)
                    } else {
                        showError(getString(R.string.error_player_not_found))
                    }
                } else {
                    showError(getString(R.string.error_connection))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showError(getString(R.string.error_connection))
            } finally {
                setLoading(false)
            }
        }
    }

    private fun displayPlayer(player: com.example.albumtracker.data.model.Player) {
        with(binding) {
            txtPlayerName.text = player.strPlayer
            txtNationality.text = getString(R.string.label_country, player.strNationality)
            txtTeam.text = getString(R.string.label_team, player.strTeam)
            txtPosition.text = getString(R.string.label_position, player.strPosition)

            Glide.with(this@SearchPlayerActivity)
                .load(player.strThumb)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(imgPlayer)
        }
    }

    private fun showError(message: String) {
        binding.txtPlayerName.text = message
        binding.imgPlayer.setImageDrawable(null)
        binding.txtNationality.text = ""
        binding.txtTeam.text = ""
        binding.txtPosition.text = ""
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.btnSearch.isEnabled = !isLoading
    }
}
