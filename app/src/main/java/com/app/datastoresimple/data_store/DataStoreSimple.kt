package com.app.datastoresimple.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSimple(private val context: Context) {

    companion object {
        private const val DATA_STORE_NAME = "data_user"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
        val KEY_NAME = stringPreferencesKey("USER_NAME")
        val KEY_AGE = stringPreferencesKey("USER_AGE")
//        val KEY_AGE = intPreferencesKey("USER_AGE")
        val KEY_BOOLEAN = booleanPreferencesKey("KEY_BOOLEAN")
        val KEY_DOUBLE = doublePreferencesKey("KEY_DOUBLE")
        val KEY_FLOAT = floatPreferencesKey("KEY_FLOAT")
    }

    val userName: Flow<String> = context.dataStore.data.map {
        it[KEY_NAME] ?: ""
    }

    val userAge: Flow<String> = context.dataStore.data.map {
        it[KEY_AGE] ?: ""
    }

    suspend fun saveUser(name: String?, age: String?) {
        context.dataStore.edit {
            it[KEY_NAME] = name ?: ""
            it[KEY_AGE] = age ?: ""
        }
    }
}