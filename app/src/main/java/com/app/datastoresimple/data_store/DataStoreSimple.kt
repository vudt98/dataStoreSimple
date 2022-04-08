package com.app.datastoresimple.data_store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.app.datastoresimple.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DataStoreSimple.DATA_STORE_NAME
)

private fun creteDataStore(context: Context) = PreferenceDataStoreFactory.create(
    corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { emptyPreferences() }
    ),
    migrations = listOf(SharedPreferencesMigration(context, "old sharePre name")),
    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
    produceFile = { context.preferencesDataStoreFile("DataStore name") }
)

class DataStoreSimple(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        const val DATA_STORE_NAME = "data_user"
        val KEY_STRING = stringPreferencesKey("KEY_STRING")
        val KEY_INT = intPreferencesKey("KEY_INT")
        val KEY_BOOLEAN = booleanPreferencesKey("KEY_BOOLEAN")
        val KEY_DOUBLE = doublePreferencesKey("KEY_DOUBLE")
        val KEY_FLOAT = floatPreferencesKey("KEY_FLOAT")
    }

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        try {
            dataStore.edit {
                it[key] = value
            }
        } catch (e: IOException) {
            //handle error
        }
    }

    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit {
            it.remove(key)
        }
    }

    suspend fun clearAllPreference() {
        dataStore.edit {
            it.clear()
        }
    }
}