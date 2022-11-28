package com.example.calendardiary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyDiary(
    @PrimaryKey val date:String,
    @ColumnInfo val title:String,
    @ColumnInfo val content:String
)
