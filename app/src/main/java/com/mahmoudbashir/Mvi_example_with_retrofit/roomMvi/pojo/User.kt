package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String?,
    val age:String?
)