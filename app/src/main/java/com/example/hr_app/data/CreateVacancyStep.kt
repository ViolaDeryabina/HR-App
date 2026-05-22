package com.example.hr_app.data

data class CreateVacancyStep(
    val title: String,
    var value: String,
    val onValueChange: (String) -> Unit,
    val main: Boolean,
    val placeholder: String,
    val iconFirst: Int? = null,
    val iconSecond: Int? = null,
    val select: Boolean = false,
    val onClick:()-> Unit={},
    val new: Boolean=false
)
