package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.mahmoudbashir.Mvi_example_with_retrofit.R
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomDataviewState
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.RoomIntent
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.ViewModelFactory
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.repository.UserRepository
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.room.UserDatabase
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.viewModel.RoomViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActivityThird : AppCompatActivity() {

    lateinit var save_btn: Button
    lateinit var getData_btn: Button
    val viewModelRoom : RoomViewModel by lazy {
        val repo = UserRepository(UserDatabase.invoke(applicationContext),this.application)
        ViewModelProviders.of(this,
            ViewModelFactory(repo)
        )[RoomViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        save_btn = findViewById(R.id.save_btn)
        getData_btn = findViewById(R.id.getData_btn)

        lifecycleScope.launchWhenStarted {
            viewModelRoom.state.collect {
                when (it) {
                    is RoomDataviewState.Idle -> Log.d("RoomState : ", "Idle")
                    is RoomDataviewState.Insert -> {
                        Log.d("RoomState : ", "Inserting ${it.user}")
                        showToast("Inserting Process :  ${it.user}")
                    }
                    is RoomDataviewState.GetStoredData -> {

                        it.user.observe(this@ActivityThird, { data ->
                            Log.d("RoomState : ", "data size : ${data.size} , data stored : $data")
                            showToast("data size : ${data.size}, $data")
                        })
                    }
                    is RoomDataviewState.Error -> Log.d(
                        "RoomState : ",
                        "error occured ${it.message}"
                    )
                }
            }
        }



        save_btn.setOnClickListener {
           lifecycleScope.launch {
               val model = User(0,"Mahmoud Bashir","25")
               viewModelRoom.insertingData(model)
               viewModelRoom.intentChannel.send(RoomIntent.InsertingData)
           }
        }

        getData_btn.setOnClickListener {
            lifecycleScope.launch {
                viewModelRoom.intentChannel.send(RoomIntent.GettingData)
            }
        }
    }

    private fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}