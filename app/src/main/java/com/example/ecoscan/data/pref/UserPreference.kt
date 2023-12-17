package com.example.ecoscan.data.pref

import android.content.Context
import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = user.email
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
            preferences[QUOTA] = user.quota
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[USER_NAME] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[QUOTA] ?: 0,
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.remove(USER_NAME)
            preferences.remove(TOKEN_KEY)
            preferences.clear()
        }
    }

    suspend fun saveResult(result: DataResultScan) {
        dataStore.edit {
            result.url
            it[calcium] = result.calcium
            it[carbon] = result.carbon
            it[emission] = result.emission
            it[fat] = result.fat
            it[foodName] = result.foodName
            it[protein] = result.protein
            it[vitamin] = result.vitamin

        }
    }
    fun getResult(): Flow<DataResultScan> {
        return dataStore.data.map {results ->
            DataResultScan(
                results[url] ?: "",
                results[calcium] ?: "",
                results[carbon] ?: "",
                results[emission] ?: "",
                results[fat] ?: "",
                results[foodName] ?: "",
                results[protein] ?: "",
                results[vitamin] ?: "",
            )
        }
    }

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        private val USER_NAME = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val QUOTA = intPreferencesKey("quota")
        private val THEME_KEY = booleanPreferencesKey("theme_setting")
        private val calcium = stringPreferencesKey("calcium")
        private val carbon = stringPreferencesKey("carbon")
        private val emission = stringPreferencesKey("emission")
        private val fat = stringPreferencesKey("fat")
        private val foodName = stringPreferencesKey("foodName")
        private val protein = stringPreferencesKey("protein")
        private val vitamin = stringPreferencesKey("vitamin")
        private val url = stringPreferencesKey("url")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return UserPreference(dataStore)

        }
    }
}