package com.example.albumtracker.ui.album

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.database.DatabaseHelper
import com.example.albumtracker.data.repository.StickerRepository

class AlbumActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: StickerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_album)

        recyclerView =
            findViewById(R.id.recyclerAlbum)

        val dbHelper =
            DatabaseHelper(this)

        repository =
            StickerRepository(dbHelper)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            StickerAdapter(
                repository.getAllStickers()
            )
    }
}