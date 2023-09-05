package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService


class FetchContentService : JobIntentService() {


    override fun onHandleWork(intent: Intent) {
    }

    // remove override and make onHandleIntent private.
    private fun onHandleIntent(intent: Intent?) {}

    // convenient method for starting the service.
    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            val task = FetchContentTask()
            task.execute()
            val productoListJSON: String = task.get()
            intent.action = "message"
            intent.putExtra("json", productoListJSON)
            enqueueWork(context, FetchContentService::class.java, 1, intent)
        }
    }
}