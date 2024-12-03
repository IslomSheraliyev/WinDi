package uz.isheraliyev.core.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object AppPreferences {
    private const val WIN_DI = "winDi"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(WIN_DI, MODE_PRIVATE)
    }

    var accessToken: String
        get() = preferences.getString(AppPreferences::accessToken.name, "") ?: ""
        set(value) {
            preferences.edit()?.putString(AppPreferences::accessToken.name, value)?.apply()
        }

    var refreshToken: String
        get() = preferences.getString(AppPreferences::refreshToken.name, "") ?: ""
        set(value) {
            preferences.edit()?.putString(AppPreferences::refreshToken.name, value)?.apply()
        }
}