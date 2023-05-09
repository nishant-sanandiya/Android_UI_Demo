package com.example.ui_demo.Utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


class SharedPreferenceUtil(private val context: Context) {

    private val preferenceName = "USER_DATA"

    private fun initializeEditorPreference(): Editor {
        val preferenceInstance = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        return preferenceInstance.edit()
    }

    private fun initializePreference(): SharedPreferences {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    fun setValue(key: String, value: String) {
        val editor = initializeEditorPreference();
        editor.putString(key, value)
        editor.apply()
    }

    fun getValue(key: String): String {
        val preferences = initializePreference();
        return preferences.getString(key,"").toString()
    }
}