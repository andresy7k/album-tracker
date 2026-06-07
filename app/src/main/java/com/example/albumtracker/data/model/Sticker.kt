package com.example.albumtracker.data.model

data class Sticker(
    val id: Int,
    val number: Int,
    val playerName: String,
    val country: String,
    var obtained: Boolean,
    var repeatedCount: Int
)