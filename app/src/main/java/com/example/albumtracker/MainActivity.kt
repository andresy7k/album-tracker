package com.example.albumtracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albumtracker.ui.dashboard.DashboardActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        startActivity(
            Intent(
                this,
                DashboardActivity::class.java
            )
        )

        finish()
    }
}