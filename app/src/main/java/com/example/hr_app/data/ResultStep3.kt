package com.example.hr_app.data

import kotlinx.serialization.Serializable

@Serializable
data class ResultStep3(
    val hr: String,
    val phone: String,
    val email: String,
    val person: String
)
