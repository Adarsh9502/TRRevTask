package com.example.trrevtask.repository

import com.example.trrevtask.api.RetrofitInstance
import com.example.trrevtask.model.Post
import com.example.trrevtask.model.PostList
import com.example.trrevtask.model.Push
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<PostList> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun pushPost(push: Push): Response<Push> {
        return RetrofitInstance.api.pushPost(push)
    }

}