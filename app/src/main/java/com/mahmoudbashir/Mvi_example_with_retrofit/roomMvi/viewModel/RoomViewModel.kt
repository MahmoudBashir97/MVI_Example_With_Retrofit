package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteDataViewState
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteIntent
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomDataviewState
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomIntent
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class RoomViewModel(private val repo:UserRepository) :ViewModel(){

    lateinit var model:User
    val intentChannel = Channel<RoomIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<RoomDataviewState>(RoomDataviewState.Idle)
    val state: StateFlow<RoomDataviewState> get() = _viewState


    init {
        processIntent()
    }

    private fun processIntent(){
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when(it){
                    is RoomIntent.InsertingData -> insertingReduce()

                    is RoomIntent.GettingData -> gettingStoredData()
                }
            }
        }
    }

    private fun insertingReduce() {
        viewModelScope.launch {
            _viewState.value = try {
                RoomDataviewState.Insert(insertingData(model))
            }catch (e:Exception){
                RoomDataviewState.Error(e.message!!)
            }
        }
    }

    private fun gettingStoredData(){
        viewModelScope.launch {
            _viewState.value = try {
                RoomDataviewState.GetStoredData(gettingData())
            }catch (e:Exception){
                RoomDataviewState.Error(e.message!!)
            }
        }
    }

    suspend fun insertingData(user: User): User {
        model = user
        repo.insert(user)
        return user
    }

    fun gettingData(): LiveData<List<User>> {
        return repo.getAllStoredUsers()
    }



    /* private fun reduce(isInsert:Boolean){
         if (model is User){
             viewModelScope.launch {
                 _viewState.value =
                     try {
                         if (isInsert) RoomDataviewState.Insert(insertingData(model as User)) else RoomDataviewState.GetStoredData(gettingData())
                     }catch (e: Exception){
                         RoomDataviewState.Error(e.message!!)
                     }
             }
         }
     }*/


}