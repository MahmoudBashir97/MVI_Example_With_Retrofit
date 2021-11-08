package com.mahmoudbashir.Mvi_example_with_retrofit

sealed class MainViewState{
    //idle
    object Idle: MainViewState()
    //Number
    data class Number(val num:Int): MainViewState()
    //Error
    data class Error(val err:String): MainViewState()
}
