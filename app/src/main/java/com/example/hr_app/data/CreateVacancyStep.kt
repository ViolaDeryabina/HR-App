package com.example.hr_app.data

data class CreateVacancyStep(
    val title: String,
    var value: String,
    val main: Boolean,
    val placeholder: String,
    val iconFirst: Int? = null,
    val iconSecond: Int? = null,
    val select: Boolean = false
)
