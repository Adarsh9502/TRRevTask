package com.example.trrevtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trrevtask.model.Push
import com.example.trrevtask.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    var myResponse: MutableLiveData<Response<Push>> = MutableLiveData()
    fun pushPost(push: Push) {
        viewModelScope.launch {
            val response = repository.pushPost(push)
            myResponse.value = response
        }
    }
}