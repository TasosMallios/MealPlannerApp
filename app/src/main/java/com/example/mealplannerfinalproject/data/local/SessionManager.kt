package com.example.mealplannerfinalproject.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_JWT_TOKEN = "jwt_token"
    }

    // Save JWT on shared preferences
    fun saveToken(token: String) {
        prefs.edit() { putString(KEY_JWT_TOKEN, token) }
    }

    // Get JWT token
    fun getToken(): String? {
        return prefs.getString(KEY_JWT_TOKEN, null)
    }

    // Remove JWT token (Logout simulation)
    fun clearToken() {
        prefs.edit() { remove(KEY_JWT_TOKEN) }
    }
}