package com.danielpriddle.drpnews.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.bumptech.glide.Glide

class GlideWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val urlToImage = inputData.getString("image_url") ?: return Result.failure()
        val file = Glide.with(context).asFile().load(urlToImage).submit().get()
        val imagePath = file.path

        val output = workDataOf("image_path" to imagePath)
        return Result.success(output)
    }
}