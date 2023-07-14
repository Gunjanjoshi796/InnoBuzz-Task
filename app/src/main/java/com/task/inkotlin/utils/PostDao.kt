package com.task.inkotlin.utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.inkotlin.model.Posts

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<Posts>

    @Query("DELETE FROM posts")
    fun clearPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePosts(posts: List<Posts>)

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPostByID(id : Int) : Posts
}