package com.example.hr_app.data

import kotlinx.serialization.Serializable
// Назначение: Модель для создания вакансии
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Serializable
data class ResultStep1(
    val title: String,
    val salaryRange: String,
    val quantity: Int,
    val depart: String,
    val positions: String,
    val experience: String,
    val city: String,
    val public: Boolean
)
