package com.danielpriddle.drpnews.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

private const val TEST_DATASTORE_NAME = "test_datastore"

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PreferencesDataStoreTests {

    private val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val testCoroutineScope =
        TestCoroutineScope(TestCoroutineDispatcher() + Job())

    private val testDataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = testCoroutineScope,
            produceFile =
            { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
        )

    private val sut: PreferencesDataStore =
        PreferencesDataStoreImpl(testDataStore)

    @Test
    fun getIsDownloadOverWifiOnlyReturnsValue() = runTest {
        val isDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        assertFalse(isDownloadOverWifiOnly)
    }

    @Test
    fun toggleDownloadOverWifiOnlySetsValue() = runTest {
        val beforeDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        sut.toggleDownloadOverWifiOnly()
        val afterDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        assertNotEquals(beforeDownloadOverWifiOnly, afterDownloadOverWifiOnly)
    }

    @After
    fun cleanUp() {
        runTest {
            File(
                ApplicationProvider.getApplicationContext<Context>().filesDir,
                "datastore"
            ).deleteRecursively()
        }
        testCoroutineScope.cancel()
    }
}