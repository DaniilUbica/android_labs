package com.example.rateapp

data class ImageRateCardData(
    val imageResId: Int,
    var selectedValue: Int = -1,
    val imageName: String = "",
    val imageDescription: String = ""
)