package com.mahmoudbashir.Mvi_example_with_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var txt_add_number:TextView
    lateinit var add_number_btn:Button
    private val viewModel: AddNumberViewModel by lazy {
        ViewModelProviders.of(this).get(AddNumberViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_add_number = findViewById(R.id.tv_number)
        add_number_btn = findViewById(R.id.add_no_btn)

         renderOnViewModel()
         //send
         add_number_btn.setOnClickListener {
             lifecycleScope.launch {
                 viewModel.intentChannel.send(MainIntent.AddNumber)
             }
         }


    }
        //render from viewModel = observing on viewModel
        private fun renderOnViewModel(){
            lifecycleScope.launchWhenStarted {
                viewModel.state.collect {
                    when(it){
                        is MainViewState.Idle -> txt_add_number.text = "Idle"
                        is MainViewState.Number ->  txt_add_number.text = it.num.toString()
                        is MainViewState.Error ->  txt_add_number.text = it.err
                    }
                }
            }
        }
}