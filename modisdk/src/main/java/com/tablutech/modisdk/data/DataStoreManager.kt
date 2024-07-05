//package com.tablutech.modi_agentapp.data
//
//import android.app.Application
//import android.content.Context
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import com.tablutech.modi_agentapp.data.model.Data
//
//import kotlinx.coroutines.flow.first
//
//
//private val Context.dataStore by preferencesDataStore(name = "subscriber_preferences")
//
//class DataStoreManager(
//    context: Context
//) {
//
//    private val dataStore = context.dataStore
//
//    suspend fun saveToken(data: Data) {
//        dataStore.edit { preferences ->
//            preferences[SharedPreferences.token] = data.token
//        }
//    }
//
//    suspend fun getToken(): String? {
//        val preferences = dataStore.data.first()
//        return preferences[SharedPreferences.token]
//    }
//
//}