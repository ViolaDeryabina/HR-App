package com.example.hr_app.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.hr_app.data.ResultStep1
import com.example.hr_app.presentation.screens.create.ResultStep2
import com.example.hr_app.presentation.screens.create.ResultStep3
import kotlinx.serialization.json.Json
// Назначение: Класс для хранения информации в памяти
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026

class SharedPref(context: Context) {

    companion object key {
        val KEY_TOKEN = "KEY_TOKEN"
        val KEY_VACANCY_ID = "KEY_VACANCY_ID"
        val KEY_VACANCY_STEP_1 = "KEY_VACANCY_STEP_1"
        val KEY_VACANCY_STEP_2 = "KEY_VACANCY_STEP_2"
        val KEY_VACANCY_STEP_3 = "KEY_VACANCY_STEP_3"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("hr_app_preferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPref.edit { putString(KEY_TOKEN, token) }
    }

    fun deleteToken() {
        sharedPref.edit { clear() }
    }

    fun getToken(): String? {
        return sharedPref.getString(KEY_TOKEN, null)
    }

    fun saveVacancyId(id: String) {
        sharedPref.edit { putString(KEY_VACANCY_ID, id) }
    }

    fun getVacancyId(): String? {
        return sharedPref.getString(KEY_VACANCY_ID, null)
    }

    fun deleteVacancyId() {
        sharedPref.edit { clear() }
    }

    fun saveResultStep1(id: ResultStep1) {
        val json = Json.encodeToString(id)
        sharedPref.edit { putString(KEY_VACANCY_STEP_1, json) }
    }

    fun getResultStep1(): ResultStep1? {
        val json = sharedPref.getString(KEY_VACANCY_STEP_1, null) ?: return null
        return Json.decodeFromString<ResultStep1>(json)
    }

    fun saveResultStep2(id: ResultStep2) {
        val json = Json.encodeToString(id)
        sharedPref.edit { putString(KEY_VACANCY_STEP_2, json) }
    }

    fun getResultStep2(): ResultStep2? {
        val json = sharedPref.getString(KEY_VACANCY_STEP_2, null) ?: return null
        return Json.decodeFromString<ResultStep2>(json)
    }

    fun saveResultStep3(id: ResultStep3) {
        val json = Json.encodeToString(id)
        sharedPref.edit { putString(KEY_VACANCY_STEP_3, json) }
    }

    fun getResultStep3(): ResultStep3? {
        val json = sharedPref.getString(KEY_VACANCY_STEP_3, null) ?: return null
        return Json.decodeFromString<ResultStep3>(json)
    }

}