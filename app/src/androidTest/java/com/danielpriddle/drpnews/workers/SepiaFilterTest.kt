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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SepiaFilterTest {
    private val imagePath =
        "/data/user/0/com.danielpriddle.drpnews/cache/image_manager_disk_cache/4a685ca1cd8e58c058acde2d24dbb409aa6ed36ca0d4f8df27ac3e993f034eb1.0"
    private lateinit var workManager: WorkManager
    private lateinit var context: Context
    private lateinit var configuration: Configuration

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
    fun testSepiaFilterWork() {
        val worker = TestListenableWorkerBuilder<SepiaFilterWorker>(
            context = context,
            inputData = workDataOf("image_path" to imagePath)
        ).build()

        // Start the work synchronously
        val result = worker.startWork().get()
        assertEquals(Result.success().javaClass, result.javaClass)
    }
}