package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteViewModel
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.repository.Repository

class ViewModelProvidersFactory (private val repo:Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemoteViewModel::class.java)) {
            return RemoteViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}