package com.example.ui_demo.Utils

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Handler
import android.util.Log

@Suppress("DEPRECATION")
 class AsyncTaskDemo(private val time: Int) : AsyncTask<String, String, String>() {

    private val handler = Handler()

    override fun doInBackground(vararg params: String?): String? {
        handler.postDelayed({
            Log.i("Finished Process", "Finished Process")
        }, (this.time*1000).toLong())
        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onProgressUpdate(values: Array<String?>) {
        super.onProgressUpdate(*values)
    }
}