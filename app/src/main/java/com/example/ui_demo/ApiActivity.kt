@file:Suppress("DEPRECATION")

package com.example.ui_demo

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui_demo.ApiModels.ApiInterface
import com.example.ui_demo.ApiModels.RetrofitClient

@Suppress("DEPRECATION")
class ApiActivity : AppCompatActivity(), BaseActivity {

    private lateinit var progress : ProgressDialog
    private lateinit var userListView : RecyclerView
    private lateinit var retryButtonView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        uiBinding()
        attachListners()
    }

    override fun uiBinding() {
        progress = ProgressDialog(this)
        progress.setCancelable(false)
        progress.setMessage("Loading")
        title = "API Demo"
        userListView = findViewById(R.id.userList)
        retryButtonView = findViewById(R.id.retryButton)
    }

    override fun attachListners() {
        retryButtonView.setOnClickListener {
            getUserList()
        }
    }

    override fun onStart() {
        super.onStart()
        getUserList()
    }

    private fun getUserList() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            progress.show()
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful) {
                    val apiData = response.body()?.data
                    val adapter = apiData?.let { UserAdapter(dataSet = it, context = this@ApiActivity) }
                    userListView.adapter = adapter
                    userListView.layoutManager = LinearLayoutManager(this@ApiActivity, LinearLayoutManager.VERTICAL, false)
                    Toast.makeText(
                        this@ApiActivity,
                        "API Called Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@ApiActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error in API", Ex.localizedMessage?.toString() ?: "Something Went Wrong" )
            }finally {
                progress.dismiss()
            }
        }

    }
}