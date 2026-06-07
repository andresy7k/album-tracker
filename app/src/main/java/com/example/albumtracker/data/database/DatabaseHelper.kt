package com.example.albumtracker.data.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

        private const val TABLE_STICKERS = "stickers"

        private const val COLUMN_ID = "id"

        private const val COLUMN_NUMBER = "number"

        private const val COLUMN_PLAYER = "playerName"

        private const val COLUMN_COUNTRY = "country"

        private const val COLUMN_OBTAINED = "obtained"

        private const val COLUMN_REPEATED = "repeatedCount"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_STICKERS(
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NUMBER INTEGER,
                $COLUMN_PLAYER TEXT,
                $COLUMN_COUNTRY TEXT,
                $COLUMN_OBTAINED INTEGER,
                $COLUMN_REPEATED INTEGER
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_STICKERS"
        )

        onCreate(db)
    }

    fun insertSticker(
        number: Int,
        playerName: String,
        country: String
    ): Long {

        val db = writableDatabase

        val values = ContentValues().apply {

            put(COLUMN_NUMBER, number)

            put(COLUMN_PLAYER, playerName)

            put(COLUMN_COUNTRY, country)

            put(COLUMN_OBTAINED, 0)

            put(COLUMN_REPEATED, 0)
        }

        return db.insert(
            TABLE_STICKERS,
            null,
            values
        )
    }

    fun getAllStickers(): Cursor {

        return readableDatabase.rawQuery(
            "SELECT * FROM $TABLE_STICKERS",
            null
        )
    }

    fun getPendingStickers(): Cursor {

        return readableDatabase.rawQuery(
            """
            SELECT *
            FROM $TABLE_STICKERS
            WHERE $COLUMN_OBTAINED = 0
            """.trimIndent(),
            null
        )
    }

    fun getObtainedStickers(): Cursor {

        return readableDatabase.rawQuery(
            """
            SELECT *
            FROM $TABLE_STICKERS
            WHERE $COLUMN_OBTAINED = 1
            """.trimIndent(),
            null
        )
    }

    fun getRepeatedStickers(): Cursor {

        return readableDatabase.rawQuery(
            """
            SELECT *
            FROM $TABLE_STICKERS
            WHERE $COLUMN_REPEATED > 0
            """.trimIndent(),
            null
        )
    }

    fun registerObtainedSticker(
        stickerId: Int
    ) {

        val db = writableDatabase

        val cursor = db.rawQuery(
            """
            SELECT
            $COLUMN_OBTAINED,
            $COLUMN_REPEATED
            FROM $TABLE_STICKERS
            WHERE $COLUMN_ID = ?
            """.trimIndent(),
            arrayOf(stickerId.toString())
        )

        if(cursor.moveToFirst()) {

            val obtained =
                cursor.getInt(0)

            val repeated =
                cursor.getInt(1)

            val values = ContentValues()

            if(obtained == 1) {

                values.put(
                    COLUMN_REPEATED,
                    repeated + 1
                )

            } else {

                values.put(
                    COLUMN_OBTAINED,
                    1
                )
            }

            db.update(
                TABLE_STICKERS,
                values,
                "$COLUMN_ID=?",
                arrayOf(stickerId.toString())
            )
        }

        cursor.close()
    }

    fun registerTrade(
        repeatedStickerId: Int,
        newStickerId: Int
    ) {

        val db = writableDatabase

        val repeatedCursor = db.rawQuery(
            """
            SELECT $COLUMN_REPEATED
            FROM $TABLE_STICKERS
            WHERE $COLUMN_ID = ?
            """.trimIndent(),
            arrayOf(repeatedStickerId.toString())
        )

        if(repeatedCursor.moveToFirst()) {

            val repeated =
                repeatedCursor.getInt(0)

            if(repeated > 0) {

                val repeatedValues =
                    ContentValues()

                repeatedValues.put(
                    COLUMN_REPEATED,
                    repeated - 1
                )

                db.update(
                    TABLE_STICKERS,
                    repeatedValues,
                    "$COLUMN_ID=?",
                    arrayOf(
                        repeatedStickerId.toString()
                    )
                )

                val obtainedValues =
                    ContentValues()

                obtainedValues.put(
                    COLUMN_OBTAINED,
                    1
                )

                db.update(
                    TABLE_STICKERS,
                    obtainedValues,
                    "$COLUMN_ID=?",
                    arrayOf(
                        newStickerId.toString()
                    )
                )
            }
        }

        repeatedCursor.close()
    }
}