package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi

sealed class RemoteIntent {
    object getPosts:RemoteIntent()
}