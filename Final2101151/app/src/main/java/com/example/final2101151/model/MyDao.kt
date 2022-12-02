package com.example.final2101151.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
@Dao
interface MyDao {

    @Query("select * from ToDoList order by rid desc")
    fun selectAll():List<ToDoList>

    @Insert(onConflict = IGNORE)
    suspend fun insert(todolist:ToDoList)
    @Delete
    suspend fun delete(todolist:ToDoList)
}