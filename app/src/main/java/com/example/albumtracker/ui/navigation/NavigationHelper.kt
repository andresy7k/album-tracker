package com.example.albumtracker.ui.navigation

import android.app.Activity
import android.content.Intent
import com.example.albumtracker.ui.album.AlbumActivity
import com.example.albumtracker.ui.dashboard.DashboardActivity
import com.example.albumtracker.ui.search.SearchPlayerActivity
import com.example.albumtracker.ui.statistics.StatisticsActivity

object NavigationHelper {

    fun openDashboard(
        activity: Activity
    ) {

        activity.startActivity(
            Intent(
                activity,
                DashboardActivity::class.java
            )
        )
    }

    fun openAlbum(
        activity: Activity
    ) {

        activity.startActivity(
            Intent(
                activity,
                AlbumActivity::class.java
            )
        )
    }

    fun openSearch(
        activity: Activity
    ) {

        activity.startActivity(
            Intent(
                activity,
                SearchPlayerActivity::class.java
            )
        )
    }

    fun openStatistics(
        activity: Activity
    ) {

        activity.startActivity(
            Intent(
                activity,
                StatisticsActivity::class.java
            )
        )
    }
}