package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.room.UserDatabase

class UserRepository(private val db:UserDatabase, val application: Application):IRepository {

    override suspend fun insert(user: User)  = db.dao().insert(user)

    override  fun getAllStoredUsers():LiveData<List<User>> = db.dao().getAllStoredUsers()

}