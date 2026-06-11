package com.example.albumtracker.ui.obtained

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.database.DatabaseHelper
import com.example.albumtracker.data.repository.StickerRepository
import com.example.albumtracker.ui.album.StickerAdapter

class ObtainedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: StickerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_obtained)

        recyclerView =
            findViewById(R.id.recyclerObtained)

        val dbHelper =
            DatabaseHelper(this)

        repository =
            StickerRepository(dbHelper)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            StickerAdapter(
                repository.getObtainedStickers()
            )
    }
}