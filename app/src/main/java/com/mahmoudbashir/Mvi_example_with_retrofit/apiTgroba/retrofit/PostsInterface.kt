package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.retrofit

import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.Model
import retrofit2.Response
import retrofit2.http.GET

interface PostsInterface {

    @GET("posts")
    suspend fun getPosts():Response<List<Model>>

}