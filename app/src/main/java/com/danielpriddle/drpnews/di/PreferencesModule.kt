package com.danielpriddle.drpnews.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStore
import com.danielpriddle.drpnews.data.preferences.PreferencesDataStoreImpl
import com.danielpriddle.drpnews.data.preferences.PreferencesKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): PreferencesDataStore {
        val dataStore = PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PreferencesKeys.PREFS_DATASTORE_NAME) }
        )
        return PreferencesDataStoreImpl(dataStore)
    }

}