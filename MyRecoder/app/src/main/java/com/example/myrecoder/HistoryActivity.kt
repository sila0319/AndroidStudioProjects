package com.example.myrecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myrecoder.databinding.ActivityHistoryBinding
import com.example.myrecoder.model.MyDatabase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
//    private val binding by lazy {
//        ActivityHistoryBinding.inflate(layoutInflater) //메인액티비티를 바인딩하면 안된다.
//    }
    private val adapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)


//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = adapter
//
//        CoroutineScope( Dispatchers.IO).launch { //디비 정보를 얻는건 디스팩쳐 IO를 한다.
//            val dao = MyDatabase.getInstance(this@HistoryActivity).myDao() //그냥 this를 적으면 CoroutineScope를 가르키게된다.
//            val list = dao.selectAll()
//            list.forEach{
//                Log.d("List",it.toString())
//
//            }
//            withContext(Dispatchers.Main) {
//                adapter.setData(list) //그림 그리는 부분은 main에서 그려줘야하기 때문에 따로 그리도록 설정해야한다.
//            }
//
//
//        }
    }
}