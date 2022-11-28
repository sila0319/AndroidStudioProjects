package com.example.listpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listpractice.databinding.ActivityMainBinding
import com.example.listpractice.util.DataGenerator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter=ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setItemClickListener {
            Log.d("MainActivity", "$it item clicked")
            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("roomId", adapter.getItem(it)?.name)
            startActivity(intent)
        }

        binding.buttonRandom.setOnClickListener {
            adapter.setData(DataGenerator.get())
        }
    }
}