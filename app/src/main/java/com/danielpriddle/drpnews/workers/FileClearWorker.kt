package com.danielpriddle.drpnews.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide

class FileClearWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val root = Glide.getPhotoCacheDir(context)

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
            error.printStackTrace()
            Result.failure()
        }
    }
}