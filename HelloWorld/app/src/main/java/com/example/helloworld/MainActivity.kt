package com.example.helloworld

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy{
    ActivityMainBinding.inflate(layoutInflater)
}
    private val listener = View.OnClickListener {
        try{
            val number1 = binding.editTextNumber1.text.toString().toDouble()
            val number2 = binding.editTextNumber2.text.toString().toDouble()
            binding.textViewResult.text = (number1 - number2).toString()
        }catch (e:NumberFormatException){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView.text="Hello"
        binding.buttonAdd.setOnClickListener(this) //this는 View.OnClickListener안에 존재하는 추상메소드를 한꺼번에 보내야한다.
        binding.buttonSub.setOnClickListener(listener)
        binding.buttonMul.setOnClickListener(View.OnClickListener {
            try {
                val number1 = binding.editTextNumber1.text.toString().toDouble()
                val number2 = binding.editTextNumber2.text.toString().toDouble()
                binding.textViewResult.text = (number1 * number2).toString()
            }catch (e:NumberFormatException){

            }
        })
        binding.buttonDiv.setOnClickListener{
            try {
                val number1 = binding.editTextNumber1.text.toString().toDouble()
                val number2 = binding.editTextNumber2.text.toString().toDouble()
                if(number2 != 0.0) binding.textViewResult.text = (number1 / number2).toString()
                else binding.textViewResult.text="0"
            }catch (e:NumberFormatException){

            }
        }

    }

    override fun onClick(v: View?) {
        try{
            val number1 = binding.editTextNumber1.text.toString().toDouble()
            val number2 = binding.editTextNumber2.text.toString().toDouble()
            binding.textViewResult.text=(number1+number2).toString()
        }catch (e:NumberFormatException){
            return
        }
    }
}