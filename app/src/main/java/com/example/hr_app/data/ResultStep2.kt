package com.example.hr_app.data

import kotlinx.serialization.Serializable

@Serializable
data class ResultStep2(
    val description: String,
    val requirements: String?,
    val technical: String?,
    val testTask: String?,
)
