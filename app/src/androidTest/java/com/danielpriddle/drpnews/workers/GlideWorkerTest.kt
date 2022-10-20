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
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.FutureTarget
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File
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
        mockkStatic(Glide::class)
        val mockGlideRequestManager = mockk<RequestManager>()
        val mockGlideRequestFileBuilder = mockk<RequestBuilder<File>>()
        val mockGlideFileFutureTarget = mockk<FutureTarget<File>>()
        val mockFile = mockk<File>()
        val imageFilePath = "/path/to/file"
        every { Glide.with(any<Context>()) } returns mockGlideRequestManager
        every { mockGlideRequestManager.asFile() } returns mockGlideRequestFileBuilder
        every { mockGlideRequestFileBuilder.load(IMAGE_URL) } returns mockGlideRequestFileBuilder
        every { mockGlideRequestFileBuilder.submit() } returns mockGlideFileFutureTarget
        every { mockGlideFileFutureTarget.get() } returns mockFile
        every { mockFile.path } returns imageFilePath
        val worker = TestListenableWorkerBuilder<GlideWorker>(
            context = context,
            inputData = workDataOf("image_url" to IMAGE_URL)
        ).build()

        // Start the work synchronously
        val result = worker.startWork().get()
        assertEquals(Result.success().javaClass, result.javaClass)
    }

}