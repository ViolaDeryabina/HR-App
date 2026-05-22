package com.example.hr_app.presentation.navigation

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// Назначение: Объекты для навигации
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026

@Serializable
object Authorization

@Serializable
object Registration

@Serializable
object PostLoginLoading

@Serializable
object VacanciesMain

@Serializable
object VacancyDetails


@Serializable
object CreateVacancy

@Serializable
object EditVacancy
