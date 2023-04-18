package com.example.ui_demo.ApiModels

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
//    @GET("/api/users?page={pageNumber}")
//    suspend fun getAllUsersByPage(@Path(value = "pageNumber") pageNumber: Int): Response<ResponseListUsers>

    @GET("/api/users")
    suspend fun getAllUsersByPage( @Query("page") page: Int): Response<ResponseListUsers>
}