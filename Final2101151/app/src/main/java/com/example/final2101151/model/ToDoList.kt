package com.example.final2101151.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoList(
    @PrimaryKey(autoGenerate = true) val rid:Int,
    @ColumnInfo val content:String,

)