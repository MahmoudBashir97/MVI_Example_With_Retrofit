package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.retrofit.RetrofitInstance

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class RemoteViewModel : ViewModel() {

    val intentChannel = Channel<RemoteIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<RemoteDataViewState>(RemoteDataViewState.Loading)
    val state: StateFlow<RemoteDataViewState> get() = _viewState
    var list:MutableLiveData<List<Model>> = MutableLiveData()

    init {
        processIntent()
    }

    private fun processIntent(){
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when(it){
                    is RemoteIntent.getPosts -> reduce()
                }
            }
        }
    }

    private fun reduce(){
            viewModelScope.launch {
                _viewState.value =
                  try {
                   RemoteDataViewState.Post(getPosts())
                }catch (e: Exception){
                      RemoteDataViewState.Error(e.message!!)
                }
            }
    }

    public suspend fun getPosts():List<Model>{
        var response : List<Model> = ArrayList()
        RetrofitInstance.api.getPosts().apply {
            val check = this.isSuccessful
            if (check){
                val body = this.body()
                Log.d("bodyPostsList: ","body :$body")
                list.postValue(body!!)
                response = body
            }
        }
        return response
    }

}