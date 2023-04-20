@file:Suppress("DEPRECATION")

package com.example.ui_demo.Activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ui_demo.Adapters.UserAdapter
import com.example.ui_demo.ApiModels.ApiInterface
import com.example.ui_demo.ApiModels.Data
import com.example.ui_demo.ApiModels.RetrofitClient
import com.example.ui_demo.R

class ApiActivity : AppCompatActivity(), BaseActivity {

    private lateinit var progress: ProgressDialog
    private lateinit var userListView: RecyclerView
    private lateinit var retryButtonView: Button
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var nestedScrollLayout: NestedScrollView
    private lateinit var endLoaderView : ProgressBar
    private var firstPage: Int = 1
    private var isLoading: Boolean = false
    private var currentPage: Int = 1
    private var lastPage: Int? = null
    private var listDataArray = mutableListOf<Data>()

    @RequiresApi(Build.VERSION_CODES.O)
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
        val adapter = UserAdapter(dataSet = listDataArray, context = this@ApiActivity)
        userListView.adapter = adapter
        userListView.layoutManager = LinearLayoutManager(
            this@ApiActivity, LinearLayoutManager.VERTICAL, false
        )
        retryButtonView = findViewById(R.id.retryButton)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        nestedScrollLayout = findViewById(R.id.nestedScrollView)
        endLoaderView = findViewById(R.id.bottomLoader)
    }

    override fun attachListners() {
        retryButtonView.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                progress.show()
                getUserList(firstPage)
                progress.hide()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScrollLayout.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, _, _, _ ->
                if (!v.canScrollVertically(1)) {
                    if (!isLoading && currentPage != lastPage) {
                        lifecycleScope.launchWhenCreated {
                            getUserList(currentPage + 1)
                        }
                    }
                }
            })
        }

        userListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading && currentPage != lastPage) {
                        if (dy > 0) {
                            lifecycleScope.launchWhenCreated {
                                getUserList(currentPage + 1)
                            }
                        }
                    }
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launchWhenCreated {
                swipeRefreshLayout.isRefreshing = true
                getUserList(firstPage)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenCreated {
            progress.show()
            getUserList(firstPage)
            progress.hide()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun getUserList(pageNumber: Int) {
        if (!isLoading) {
            isLoading = true
            val retrofit = RetrofitClient.getInstance()
            val apiInterface = retrofit.create(ApiInterface::class.java)
            try {
                val response = apiInterface.getAllUsersByPage(pageNumber)
                if (response.isSuccessful) {
                    val apiData = response.body()?.data
                    val apiBody = response.body()
                    if (apiBody != null && apiData != null) {
                        if (apiBody.page == firstPage) {
                            listDataArray.clear()
                            listDataArray.addAll(apiData)
                        } else {
                            listDataArray.addAll(apiData)
                        }
                        if(apiBody.page == lastPage){
                            endLoaderView.visibility = View.GONE
                        }else{
                            endLoaderView.visibility = View.VISIBLE
                        }
                        currentPage = apiBody.page
                        lastPage = apiBody.totalPages
                    }
                    userListView.adapter?.notifyDataSetChanged()
                    Toast.makeText(
                        this@ApiActivity, "API Called Successfully", Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@ApiActivity, response.errorBody().toString(), Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.i("Error in API", Ex.localizedMessage?.toString() ?: "Something Went Wrong")
                Toast.makeText(
                    this@ApiActivity, "Something Went Wrong", Toast.LENGTH_LONG
                ).show()
            } finally {
                isLoading = false
            }
        }
    }
}


