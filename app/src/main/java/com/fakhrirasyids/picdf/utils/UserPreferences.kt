package com.fakhrirasyids.picdf.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fakhrirasyids.picdf.utils.Constants.IS_STARTER_SHOWN
import com.fakhrirasyids.picdf.utils.Constants.isStarterShown
import com.fakhrirasyids.picdf.utils.Constants.preferenceDefaultValue
import com.fakhrirasyids.picdf.utils.Constants.preferenceName
import kotlinx.coroutines.flow.map


class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun setIsStarterShown() {
        dataStore.edit { prefs ->
            prefs[isStarterShown] = IS_STARTER_SHOWN
        }
    }

    fun isStarterShown() = dataStore.data.map { it[isStarterShown] ?: preferenceDefaultValue }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}