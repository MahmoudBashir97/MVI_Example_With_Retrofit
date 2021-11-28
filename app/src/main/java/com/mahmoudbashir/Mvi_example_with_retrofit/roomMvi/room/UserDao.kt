package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User)

    @Query("SELECT * FROM User ORDER BY id")
    fun getAllStoredUsers():LiveData<List<User>>

}