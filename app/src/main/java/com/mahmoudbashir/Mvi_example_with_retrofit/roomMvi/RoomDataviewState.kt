package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi

import androidx.lifecycle.LiveData
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User

sealed class RoomDataviewState {

    // start point of state
    object Idle:RoomDataviewState()
    // inserting data to room db
    data class Insert(val user:User):RoomDataviewState()
    // getting data from room db
    data class GetStoredData(val user:LiveData<List<User>>):RoomDataviewState()
    // show error message
    data class Error(val message:String):RoomDataviewState()

}