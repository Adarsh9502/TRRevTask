package com.example.trrevtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trrevtask.model.Post
import com.example.trrevtask.model.PostList
import com.example.trrevtask.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ShowDetailsViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<PostList>> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

}