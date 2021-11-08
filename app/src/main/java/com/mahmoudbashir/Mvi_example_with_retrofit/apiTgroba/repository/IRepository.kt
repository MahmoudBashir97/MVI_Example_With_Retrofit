package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.repository

import com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba.Model
import retrofit2.Response

interface IRepository {
    suspend fun getPosts():Response<List<Model>>
}