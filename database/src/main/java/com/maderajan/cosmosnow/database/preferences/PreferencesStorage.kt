package com.maderajan.cosmosnow.database.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    companion object {
        const val NAME = "cosmos-now.preferences"

        private val NEWS_FONT_SIZE = intPreferencesKey("news_font_size")
    }

    fun getFontSize(): Flow<Int> = dataStore.data.map { preferences ->
        preferences[NEWS_FONT_SIZE] ?: 16
    }

    suspend fun updateFontSize(fontSize: Int) {
        dataStore.edit { preferences ->
            preferences[NEWS_FONT_SIZE] = fontSize
        }
    }
}