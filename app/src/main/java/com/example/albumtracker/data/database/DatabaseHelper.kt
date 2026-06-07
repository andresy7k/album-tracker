package com.example.albumtracker.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {

        private const val DATABASE_NAME = "album.db"

        private const val DATABASE_VERSION = 1

    }
}

override fun onCreate(db: SQLiteDatabase) {

}
val createTable = """
CREATE TABLE stickers(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    number INTEGER,
    playerName TEXT,
    country TEXT,
    obtained INTEGER,
    repeatedCount INTEGER
)
"""