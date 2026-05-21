package com.example.hr_app.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPref(context: Context) {

    companion object key {
        val KEY_TOKEN = "KEY_TOKEN"
        val KEY_VACANCY_ID = "KEY_VACANCY_ID"
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
}