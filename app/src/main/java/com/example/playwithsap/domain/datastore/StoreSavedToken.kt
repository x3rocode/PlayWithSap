package com.example.playwithsap.domain.datastore

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext


object StoreSavedToken {
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var sharedPrefsEditor : SharedPreferences.Editor

    fun setAuthToken(@ApplicationContext context: Context, user: String, password: String){
        var authToken = "Basic " + Base64.encodeToString("${user}:${password}".toByteArray(), Base64.NO_WRAP)
        sharedPreferences = EncryptedSharedPreferences.create(
            "android",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        sharedPrefsEditor = sharedPreferences.edit()
        sharedPrefsEditor?.apply {
            putString("authToken", authToken)
        }
        sharedPrefsEditor.apply()
    }

    fun getAuthToken() : String{
        return sharedPreferences.getString("authToken", "")!!
    }

}