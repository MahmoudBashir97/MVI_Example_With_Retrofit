package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi

sealed class RoomIntent{
    object InsertingData:RoomIntent()
    object GettingData:RoomIntent()

}
