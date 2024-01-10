package com.example.demo.ui.shared

import android.content.SharedPreferences

fun saveUser(sharedPreferences: SharedPreferences, token: String, name: String) {
    sharedPreferences.edit().putString("token", token).apply()
    sharedPreferences.edit().putString("name", name).apply()
}