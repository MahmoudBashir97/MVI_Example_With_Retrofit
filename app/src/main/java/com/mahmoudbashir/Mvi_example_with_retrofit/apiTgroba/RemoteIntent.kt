package com.mahmoudbashir.Mvi_example_with_retrofit.apiTgroba

sealed class RemoteIntent {
    object getPosts:RemoteIntent()
}