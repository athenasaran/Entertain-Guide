package com.athena.entertainguide.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

internal class SharedPreferencesManagerImpl(
    context: Context
) : SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("ENTERTAIN", Context.MODE_PRIVATE)

    override fun putString(key: String, string: String) {
        sharedPreferences.edit().putString(key, string).apply()
    }

    override fun putBoolean(key: String, boolean: Boolean) {
        sharedPreferences.edit().putBoolean(key, boolean).apply()
    }

    override fun putInt(key: String, integer: Int) {
        sharedPreferences.edit().putInt(key, integer).apply()
    }

    override fun putLong(key: String, long: Long) {
        sharedPreferences.edit().putLong(key, long).apply()
    }

    override fun putStringSet(key: String, string: Set<String>) {
        sharedPreferences.edit().putStringSet(key, string).apply()
    }

    override fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    override fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    override fun getStringSet(key: String): MutableSet<String>? {
        return sharedPreferences.getStringSet(key, null)
    }
}
