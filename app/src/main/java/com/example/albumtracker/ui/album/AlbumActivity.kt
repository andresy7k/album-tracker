package com.example.albumtracker.ui.album

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.database.DatabaseHelper
import com.example.albumtracker.data.repository.StickerRepository

class AlbumActivity : AppCompatActivity() {

    private lateinit var repository: StickerRepository

    private lateinit var adapter: StickerAdapter

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_album
        )

        repository =
            StickerRepository(
                DatabaseHelper(this)
            )

        val recycler =
            findViewById<RecyclerView>(
                R.id.recyclerAlbum
            )

        recycler.layoutManager =
            LinearLayoutManager(this)

        adapter =
            StickerAdapter(
                repository.getAllStickers()
                    .toMutableList(),

                onObtained = {

                    repository
                        .registerObtainedSticker(
                            it.id
                        )

                    reloadAll()
                },

                onRepeated = {

                    repository
                        .registerObtainedSticker(
                            it.id
                        )

                    reloadAll()
                }
            )

        recycler.adapter =
            adapter

        findViewById<Button>(
            R.id.btnAll
        ).setOnClickListener {

            adapter.updateData(
                repository
                    .getAllStickers()
                    .toMutableList()
            )
        }

        findViewById<Button>(
            R.id.btnObtained
        ).setOnClickListener {

            adapter.updateData(
                repository
                    .getObtainedStickers()
                    .toMutableList()
            )
        }

        findViewById<Button>(
            R.id.btnPending
        ).setOnClickListener {

            adapter.updateData(
                repository
                    .getPendingStickers()
                    .toMutableList()
            )
        }

        findViewById<EditText>(
            R.id.etSearch
        ).setOnEditorActionListener {

                _, _, _ ->

            val text =
                findViewById<EditText>(
                    R.id.etSearch
                ).text.toString()

            val filtered =
                repository
                    .getAllStickers()
                    .filter {

                        it.playerName
                            .contains(
                                text,
                                true
                            )
                    }

            adapter.updateData(
                filtered.toMutableList()
            )

            true
        }
    }

    private fun reloadAll() {

        adapter.updateData(
            repository
                .getAllStickers()
                .toMutableList()
        )
    }
}