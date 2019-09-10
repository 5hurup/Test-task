package ru.task.app.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import org.joda.time.DateTime

internal class SharedPrefs(context: Context) {
    companion object {
        const val SHARED_PREFS_KEY = "task_test_shared_prefs"
        const val KEY_CONTACTS_LAST_UPDATE_TIME = "contacts_last_update_time"

        private fun SharedPreferences.getStringOrNull(key: String): String? =
            getString(key, "").ifBlank { null }

        private fun SharedPreferences.putStringOrNull(key: String, value: String?) =
            edit { if (value == null) remove(key) else putString(key, value) }
    }

    private val prefs = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

    var contactsLastUpdateTime: DateTime?
        get() = prefs.getStringOrNull(KEY_CONTACTS_LAST_UPDATE_TIME)?.let { DateTime(it) }
        set(value) = prefs.putStringOrNull(KEY_CONTACTS_LAST_UPDATE_TIME, value?.toString())

    fun flushPrefs() {
        prefs.edit { clear() }
    }
}