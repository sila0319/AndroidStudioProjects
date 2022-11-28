package com.example.calendardiary.model

import androidx.room.Dao

import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface MyDao {

    @Query("SELECT * FROM MyDiary WHERE date = :date")
    suspend fun select(date:String): List<MyDiary>


    @Insert(onConflict = REPLACE)
    suspend fun insert(diary: MyDiary)

    @Delete
    suspend fun delete(diary: MyDiary)





}