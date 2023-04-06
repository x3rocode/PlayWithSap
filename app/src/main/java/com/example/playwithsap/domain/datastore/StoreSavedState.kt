package com.example.playwithsap.domain.datastore

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext


class StoreSavedState(@ApplicationContext context: Context, authToken: String) {

    var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        authToken,
        "authToken",
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}