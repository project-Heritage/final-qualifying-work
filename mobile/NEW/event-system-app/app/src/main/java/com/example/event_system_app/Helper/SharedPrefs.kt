package com.example.event_system_app.Helper

import android.content.Context
import android.content.SharedPreferences


val PREFERENCE_NAME = "SharesPreference"
val PREFERENCE_LANGUAGE = "Language"

class SharedPrefs(context: Context){
    val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getLanguageCount(): String{
        return preferences.getString(PREFERENCE_LANGUAGE, "ru")!!
    }

    fun setLanguageCount(language: String) {
        val editor = preferences.edit()
        editor.putString(PREFERENCE_LANGUAGE, language)
        editor.apply()
    }

}