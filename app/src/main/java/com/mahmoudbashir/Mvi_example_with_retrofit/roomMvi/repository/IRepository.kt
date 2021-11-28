package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository

import androidx.lifecycle.LiveData
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User

interface IRepository {
    suspend fun insert(user:User)
    fun getAllStoredUsers():LiveData<List<User>>
}