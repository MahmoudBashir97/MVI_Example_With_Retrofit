package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi

import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.pojo.Model

sealed class RemoteDataViewState {

    //loading
    object Loading:RemoteDataViewState()
    //post data
    data class Post(val posts:List<Model>):RemoteDataViewState()
    //Error
    data class Error(val err:String):RemoteDataViewState()
}