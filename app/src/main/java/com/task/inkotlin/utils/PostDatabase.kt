package com.task.inkotlin.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task.inkotlin.model.Posts

@Database(entities = [Posts::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var instance: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "Db_Name"
                )
                    .allowMainThreadQueries()
                    .build()
                instance = newInstance
                newInstance
            }
        }
    }
}