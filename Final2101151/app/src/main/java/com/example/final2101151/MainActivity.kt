package com.example.final2101151


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final2101151.databinding.ActivityMainBinding

import android.content.Intent
import kotlinx.coroutines.*
import android.util.Log
import com.example.final2101151.model.*
import java.time.LocalDateTime


class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = ListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val dao = MyDatabase.getInstance(this@MainActivity).myDao()
            val list = dao.selectAll()
            withContext(Dispatchers.Main){
                clear()
                adapter.setData(list)
            }
        }

        binding.saveButton.setOnClickListener{
            val content = binding.editToDoList.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val dao = MyDatabase.getInstance(this@MainActivity).myDao()
                dao.insert(ToDoList(0, content))
                val list = dao.selectAll()
                withContext(Dispatchers.Main){
                    clear()
                    adapter.setData(list)
                }
            }
        }


    }
    fun clear (){
        binding.editToDoList.setText("")
    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}