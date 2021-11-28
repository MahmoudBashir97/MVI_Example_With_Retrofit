package com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmoudbashir.Mvi_example_with_retrofit.roomMvi.pojo.User

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun dao():UserDao

   companion object{
       @Volatile
       private var instance:UserDatabase?=null
       private val LOCK = Any()
       operator fun invoke(context:Context) = instance?: synchronized(LOCK){
           instance?: createDatabase(context).also { instance=it }
       }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
           context.applicationContext,
           UserDatabase::class.java,
           "userList_db"
       ).build()
   }

}