package com.athena.entertainguide.sharedPreferences

interface SharedPreferencesManager {
    fun putString(key: String, string: String)
    fun putBoolean(key: String, boolean: Boolean)
    fun putInt(key: String, integer: Int)
    fun putLong(key: String, long: Long)
    fun putStringSet(key: String, string: Set<String>)
    fun getString(key: String): String?
    fun getBoolean(key: String): Boolean
    fun getInt(key: String): Int
    fun getLong(key: String): Long
    fun getStringSet(key: String): MutableSet<String>?
    fun remove(key: String)
    fun contains(key: String): Boolean
}
