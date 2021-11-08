package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.repository

import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.Model
import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.retrofit.RetrofitInstance
import retrofit2.Response

class Repository : IRepository{
    override suspend fun getPosts(): Response<List<Model>> = RetrofitInstance.api.getPosts()
}