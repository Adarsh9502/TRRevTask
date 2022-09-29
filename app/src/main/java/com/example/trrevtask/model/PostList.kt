package com.example.trrevtask.model

import com.google.gson.annotations.SerializedName

data class PostList (
    @SerializedName("data")
    var post: List<Post>)