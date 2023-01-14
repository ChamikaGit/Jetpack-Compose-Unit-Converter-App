package com.chami.composeunitconverterapplication.data

data class Conversion(
    val id: Int,
    val description: String,
    val convertFrom: String,
    val convertTo: String,
    val multipleBy: Double
)