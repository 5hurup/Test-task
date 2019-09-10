package ru.task.app.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.joda.time.DateTime

internal class SharedPrefs(context: Context) {
    companion object {
        const val SHARED_PREFS_KEY = "skbkontur_shared_prefs"
        const val KEY_CONTACTS_LAST_UPDATE_TIME = "contacts_last_update_time"

        private fun SharedPreferences.getLongOrNull(key: String): Long? =
            getLong(key, Long.MIN_VALUE).takeIf { it != Long.MIN_VALUE }

        private fun SharedPreferences.putLongOrNull(key: String, value: Long?) =
            edit { if (value == null) remove(key) else putLong(key, value) }
    }

    private val prefs = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

    var contactsLastUpdateTime: DateTime?
        get() = prefs.getLongOrNull(KEY_CONTACTS_LAST_UPDATE_TIME)?.let { DateTime(it) }
        set(value) = prefs.putLongOrNull(KEY_CONTACTS_LAST_UPDATE_TIME, value?.millis)

    fun flushPrefs() {
        prefs.edit { clear() }
    }
}