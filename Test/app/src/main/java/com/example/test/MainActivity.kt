package com.example.test

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , View.OnClickListener{
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener(this) //가위
        binding.button2.setOnClickListener(this) //바위
        binding.button3.setOnClickListener(this) //보
        binding.button4.setOnClickListener(this) // 초기화
    }
    val arr = intArrayOf(0,0,0)

    //0이면 가위 1이면 바위 2이면 보
    override fun onClick(v:View?){
        try{
            val num =(0..2).random()
            if(v?.id==R.id.button) {
                when(num){
                    0 ->  {
                        ++arr[1]
                        binding.textView4.text = "무"
                    }
                    1 ->  {
                        ++arr[2]
                        binding.textView4.text = "패"
                    }
                    2 ->  {
                        ++arr[0]
                        binding.textView4.text = "승"
                    }
                }
            }
            if(v?.id==R.id.button2){
                when(num){
                    1 -> {
                        ++arr[1]
                        binding.textView4.text = "무"
                    }
                    2 ->  {
                        ++arr[2]
                        binding.textView4.text = "패"
                    }
                    0 ->  {
                        ++arr[0]
                        binding.textView4.text = "승"
                    }
                }
            }
            if(v?.id==R.id.button3){
                when(num){
                    2 -> {
                       ++arr[1]
                        binding.textView4.text = "무"
                    }
                    0 -> {
                        ++arr[2]
                        binding.textView4.text = "패"
                    }
                    1 -> {
                        ++arr[0]
                        binding.textView4.text = "승"
                    }
                }
            }
            if(v?.id==R.id.button4){
                for(i in (0..2)){
                    arr[i] =0
                }
                arr[0] = 0
                arr[1] = 0
                arr[2] = 0
                binding.textView4.text= "결과 출력창"

            }
            binding.textViewResult.text="${arr[0]} 승 ${arr[1]}무 ${arr[2]}패"
        }catch(e: Exception){
            return
        }
    }


}