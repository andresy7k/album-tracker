package com.example.albumtracker.data.database

class DatabaseSeeder(
    private val databaseHelper: DatabaseHelper
) {

    fun seed() {

        databaseHelper.insertSticker(
            1,
            "Lionel Messi",
            "Argentina"
        )

        databaseHelper.insertSticker(
            2,
            "Julian Alvarez",
            "Argentina"
        )

        databaseHelper.insertSticker(
            3,
            "Kylian Mbappe",
            "France"
        )

        databaseHelper.insertSticker(
            4,
            "Vinicius Junior",
            "Brazil"
        )

        databaseHelper.insertSticker(
            5,
            "Jude Bellingham",
            "England"
        )
    }
}