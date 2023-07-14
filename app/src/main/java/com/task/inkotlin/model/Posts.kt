package com.task.inkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Posts (
    @PrimaryKey(autoGenerate = true)
    var primeKey : Int,
    @ColumnInfo(name = "userId")
    var userId : Int,
    @ColumnInfo(name = "id")
             var id : Int,
    @ColumnInfo(name = "title")
             var title : String,
    @ColumnInfo(name = "body")
             var body : String  )  {
}