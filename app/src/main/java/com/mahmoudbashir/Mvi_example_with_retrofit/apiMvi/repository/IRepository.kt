package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.repository

import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.pojo.Model
import retrofit2.Response

interface IRepository {
    suspend fun getPosts():Response<List<Model>>
}