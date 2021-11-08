package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba

sealed class RemoteDataViewState {

    //loading
    object Loading:RemoteDataViewState()
    //post data
    data class Post(val posts:List<Model>):RemoteDataViewState()
    //Error
    data class Error(val err:String):RemoteDataViewState()

}