package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteViewModel
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.repository.Repository
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository.UserRepository
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.viewModel.RoomViewModel

class ViewModelFactory (val model:Any,private val repo:UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(model,repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}