package com.example.myapplication

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class FetchContentTask :
    AsyncTask<Void?, Void?, String>() {

    override fun doInBackground(vararg p0: Void?): String {

        val URL: String = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url(URL)
            .build()
        val response:Response = okHttpClient.newCall(request).execute()
        val responseBody: ResponseBody = response.body!!
        return responseBody.string()
    }
}