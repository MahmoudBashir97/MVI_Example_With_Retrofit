package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.repository

import android.app.Application
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.pojo.Model
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.retrofit.RetrofitInstance
import retrofit2.Response

class Repository(val application: Application): IRepository{
    override suspend fun getPosts(): Response<List<Model>> = RetrofitInstance.api.getPosts()
}