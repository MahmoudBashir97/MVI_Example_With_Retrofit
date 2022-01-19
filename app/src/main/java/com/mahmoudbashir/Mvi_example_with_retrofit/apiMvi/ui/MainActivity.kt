package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.Mvi_example_with_retrofit.R
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.pojo.Model
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteDataViewState
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteIntent
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.RemoteViewModel
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.adapters.PostsAdapter
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.repository.Repository
import com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.utils.ViewModelProvidersFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var post_adpt : PostsAdapter
    lateinit var rec_posts:RecyclerView
    lateinit var pro_bar:ProgressBar
    var mlist:List<Model> = ArrayList()
    val viewModel: RemoteViewModel by lazy {
        val repo = Repository(this.application)
         ViewModelProviders.of(this,ViewModelProvidersFactory(repo))[RemoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        rec_posts = findViewById(R.id.rec_posts)
        pro_bar = findViewById(R.id.pro_bar)



        renderOnViewModel()
        //send
        lifecycleScope.launch {
            viewModel.intentChannel.send(RemoteIntent.getPosts)
        }

    }

    private fun setUpRecyclerView(){

        post_adpt = PostsAdapter(mlist)
        rec_posts.apply {
            setHasFixedSize(true)
            adapter = post_adpt
        }
    }

    //render from viewModel = observing on viewModel
    private fun renderOnViewModel(){
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when(it){
                    is RemoteDataViewState.Loading ->
                    {
                        Log.d("dataL : "," Loading")
                        pro_bar.visibility = View.VISIBLE
                    }
                    is RemoteDataViewState.Post -> {
                        Log.d("dataL : ", " success : ${it.posts}")
                        mlist = it.posts
                        pro_bar.visibility = View.GONE
                        rec_posts.visibility = View.VISIBLE
                        setUpRecyclerView()
                    }
                    is RemoteDataViewState.Error ->
                    {
                        Log.d("dataL : "," Error")
                        pro_bar.visibility = View.GONE
                    }

                }
            }
        }
    }
}