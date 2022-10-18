package com.danielpriddle.drpnews.workers

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker.Result
import androidx.work.WorkManager
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.workDataOf
import com.bumptech.glide.Glide
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.reflect.typeOf

class GlideWorkerTest {
    private lateinit var workManager: WorkManager
    private lateinit var context: Context
    private lateinit var configuration: Configuration
    private val IMAGE_URL = "https://images.wsj.net/im-646132/social"

    @Before
    fun setup() {
        // Configure WorkManager
        configuration = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        context = InstrumentationRegistry.getInstrumentation().targetContext
        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testGlideWork() {
        val worker = TestListenableWorkerBuilder<GlideWorker>(
            context = context,
            inputData = workDataOf("image_url" to IMAGE_URL)
        ).build()

        // Start the work synchronously
        val result = worker.startWork().get()
        assertEquals(Result.success().javaClass, result.javaClass)
    }

}