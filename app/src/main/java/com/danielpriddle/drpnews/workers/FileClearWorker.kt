package com.danielpriddle.drpnews.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.danielpriddle.drpnews.utils.Logger

class FileClearWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters), Logger {

    override fun doWork(): Result {
        val root = Glide.getPhotoCacheDir(context)
        logDebug("FileClearWorker - Glide Root: $root")
        return try {
            root?.listFiles()?.forEach { child ->
                if (child.isDirectory) {
                    child.deleteRecursively()
                } else {
                    child.delete()
                }
            }
            Result.success()
        } catch (error: Throwable) {
            logError("FileClearWorker - Error: ${error.message}")
            Result.failure()
        }
    }
}