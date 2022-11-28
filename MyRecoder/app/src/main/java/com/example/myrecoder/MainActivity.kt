package com.example.myrecoder

import android.content.Intent
import kotlinx.coroutines.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.myrecoder.model.MyDatabase
import com.example.myrecoder.model.MyRecord
import com.example.myrecoder.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener{
            val food = binding.editTextFoodName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val time = LocalDateTime.now().toString()
                val db = MyDatabase.getInstance(this@MainActivity)
                db?.myDao()?.insert(MyRecord(0,food,time))
            }

        }

        //버튼을 누르게 되면 HistoryActivity로 넘어가게 된다.
        binding.buttonHistory.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
    }


}