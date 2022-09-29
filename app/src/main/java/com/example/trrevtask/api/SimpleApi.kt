package com.example.trrevtask.api

import com.example.trrevtask.model.Post
import com.example.trrevtask.model.PostList
import com.example.trrevtask.model.Push
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {

    @GET("get_department")
    suspend fun getPost(): Response<PostList>

    @POST("register")
    suspend fun pushPost(
        @Body push: Push
    ): Response<Push>

}