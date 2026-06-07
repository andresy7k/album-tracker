package com.example.albumtracker.ui.dashboard

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.R
import com.example.albumtracker.data.database.DatabaseHelper
import com.example.albumtracker.data.database.DatabaseSeeder
import com.example.albumtracker.data.repository.StickerRepository

class DashboardActivity : AppCompatActivity() {

    private lateinit var repository: StickerRepository

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_dashboard
        )

        val helper =
            DatabaseHelper(this)

        DatabaseSeeder(helper).seed()

        repository =
            StickerRepository(helper)

        val obtained =
            repository
                .getObtainedStickers()

        val pending =
            repository
                .getPendingStickers()

        val repeated =
            repository
                .getRepeatedStickers()

        val total =
            obtained.size +
                    pending.size

        val percentage =
            if(total > 0)
                (obtained.size * 100) / total
            else
                0

        findViewById<TextView>(
            R.id.txtObtained
        ).text = obtained.size.toString()

        findViewById<TextView>(
            R.id.txtPending
        ).text = pending.size.toString()

        findViewById<TextView>(
            R.id.txtRepeated
        ).text = repeated.size.toString()

        findViewById<TextView>(
            R.id.txtProgress
        ).text = "$percentage%"

        findViewById<TextView>(
            R.id.txtCompleted
        ).text =
            "${obtained.size}/$total"

        findViewById<ProgressBar>(
            R.id.progressBar
        ).progress = percentage

        val recycler =
            findViewById<RecyclerView>(
                R.id.recyclerRecent
            )

        recycler.layoutManager =
            LinearLayoutManager(this)

        recycler.adapter =
            DashboardAdapter(
                repository
                    .getAllStickers()
                    .take(5)
            )
    }
}