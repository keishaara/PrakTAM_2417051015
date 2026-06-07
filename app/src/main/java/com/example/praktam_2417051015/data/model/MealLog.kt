package com.example.praktam_2417051015.data.model

data class MealLog(
    val id: String = java.util.UUID.randomUUID().toString(),
    val foodName: String,
    val timeFormatted: String
)
