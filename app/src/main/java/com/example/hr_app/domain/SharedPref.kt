package com.example.hr_app.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPref(context: Context) {

    companion object key{
        val key_token="KEY_TOKEN"
    }

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("hr_app_preferences", Context.MODE_PRIVATE)

    fun saveToken(token: String){
        sharedPref.edit { putString(key_token, token) }
    }

    fun deleteToken(){
        sharedPref.edit { clear() }
    }

    fun getToken(): String?{
        return sharedPref.getString(key_token, null)
    }
}