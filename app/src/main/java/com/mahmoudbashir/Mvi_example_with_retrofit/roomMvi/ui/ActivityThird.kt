package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.mahmoudbashir.Mvi_example_with_retrofit.R
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomDataviewState
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomIntent
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.ViewModelFactory
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository.UserRepository
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.room.UserDatabase
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.viewModel.RoomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActivityThird : AppCompatActivity() {

    lateinit var save_btn:Button
    lateinit var getData_btn:Button
    lateinit var viewModel:RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        save_btn = findViewById(R.id.save_btn)
        getData_btn = findViewById(R.id.getData_btn)

        setupViewModel()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                when(it){
                    is RoomDataviewState.Idle -> Log.d("RoomState : ","Idle")
                    is RoomDataviewState.Insert -> {
                        Log.d("RoomState : ","Inserting ${it.user}")

                    }
                    is RoomDataviewState.GetStoredData -> {

                        it.user.observe(this@ActivityThird, Observer {data->
                            Log.d("RoomState : ","data size : ${data.size} , data stored : $data")
                        })
                    }
                    is RoomDataviewState.Error -> Log.d("RoomState : ","error occured ${it.message}")
                }
            }
        }

        save_btn.setOnClickListener {
            val m = User(12355,"Mido El gamed awii da l how ana y3nii","24")
            viewModel.saveModel(m)
            lifecycleScope.launch {
                viewModel.intentChannel.send(RoomIntent.InsertingData)
            }
        }

        lifecycleScope.launch {
            viewModel.intentChannel.send(RoomIntent.GettingData)
        }
        getData_btn.setOnClickListener {

            lifecycleScope.launch {
                viewModel.intentChannel.send(RoomIntent.GettingData)
            }
           /* viewModel.gettingData().observe(this, Observer {
                Log.d("RoomState : ","size : ${it.size}")
            })*/

        }

    }

    private fun setupViewModel() {
        val model = User(0,"Mahmoud","25")
        val repo = UserRepository(UserDatabase.invoke(applicationContext),application)
        viewModel = ViewModelProviders.of(this,ViewModelFactory(model,repo))[RoomViewModel::class.java]
    }
}