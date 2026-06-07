package com.example.albumtracker.data.repository


import android.database.Cursor
import com.example.albumtracker.data.database.DatabaseHelper
import com.example.albumtracker.data.model.Sticker

class StickerRepository(
    private val dbHelper: DatabaseHelper
) {

    fun getAllStickers(): List<Sticker> {

        val stickers = mutableListOf<Sticker>()

        val cursor = dbHelper.getAllStickers()

        while(cursor.moveToNext()) {

            stickers.add(
                Sticker(
                    id = cursor.getInt(0),
                    number = cursor.getInt(1),
                    playerName = cursor.getString(2),
                    country = cursor.getString(3),
                    obtained = cursor.getInt(4) == 1,
                    repeatedCount = cursor.getInt(5)
                )
            )
        }

        cursor.close()

        return stickers
    }

    fun getPendingStickers(): List<Sticker> {

        val stickers = mutableListOf<Sticker>()

        val cursor = dbHelper.getPendingStickers()

        while(cursor.moveToNext()) {

            stickers.add(
                Sticker(
                    id = cursor.getInt(0),
                    number = cursor.getInt(1),
                    playerName = cursor.getString(2),
                    country = cursor.getString(3),
                    obtained = cursor.getInt(4) == 1,
                    repeatedCount = cursor.getInt(5)
                )
            )
        }

        cursor.close()

        return stickers
    }

    fun getObtainedStickers(): List<Sticker> {

        val stickers = mutableListOf<Sticker>()

        val cursor = dbHelper.getObtainedStickers()

        while(cursor.moveToNext()) {

            stickers.add(
                Sticker(
                    id = cursor.getInt(0),
                    number = cursor.getInt(1),
                    playerName = cursor.getString(2),
                    country = cursor.getString(3),
                    obtained = cursor.getInt(4) == 1,
                    repeatedCount = cursor.getInt(5)
                )
            )
        }

        cursor.close()

        return stickers
    }

    fun registerSticker(
        stickerId: Int
    ) {

        dbHelper.registerObtainedSticker(
            stickerId
        )
    }
}