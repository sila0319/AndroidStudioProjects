package com.example.register

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.register.databinding.ActivityMainBinding

class MyCycle: DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner){
        Log.i("MainActivity", "Mycycle-Started")
    }
    override fun onStop(owner: LifecycleOwner){
        Log.i("MainActivity", "Mycycle-Stopped")
    }
}

class MainActivity : AppCompatActivity(){
    private lateinit var myCycle: MyCycle
    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    //main -> course -> main 으로 다시 돌아올 경우 데이터 값을 받아온다.
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
         val result = it.resultCode
         Log.d("MainActiviry","Activity result code: $result")
        }
    // 명시적 Intent
    private val onApply = View.OnClickListener {
        var course = when(binding.radioGroup.checkedRadioButtonId){
            R.id.radioButtonAdult -> "성인반"
            else -> "학생반"
        }
        //해당 intent로 이동하기전에 puyExtra 형식으로 데이터를 담는다.
        val intent = Intent(this, courseActivity:: class.java).apply {
            putExtra("name", binding.editTextTextPersonName.text.toString())
            putExtra("course", course)
        }
        //startActivity(intent)
        getResult.launch(intent) //자동적으로 결과값을 리턴해서 받아온다.

    }

    private fun updateWidgets(){ //widget업데이트
        var progress=0 //progress를 초기화해준다.
        if(binding.editTextTextPersonName.text.isNotEmpty()) //사람이름 텍스트가 비어있지않다면
            progress++ // 프로그래스바1을 증가.
        if(binding.editTextPhone.text.isNotEmpty()) //휴대폰 텍스트가 비어있지 않다면
            progress++ //프로그래스바 1을 증가
        // check radio - 선택된 버튼이 없을 때 -1
        if(binding.radioGroup.checkedRadioButtonId > -1)
            progress++
        if(binding.checkBoxEURA.isChecked) //이용약관동의가 체크되어있다면 true
            progress++ //프로그래스바 1을 증가

        binding.progressBar.progress = progress //증가한 프로그래스바를 설정해준다.

        //프로그래스바 맥스가4이므로 모든 버튼들이 활성화 되어있다면 전송버튼을 활성화한다.
        binding.buttonApply.isEnabled = progress==binding.progressBar.max
    }





    private val textWatcher:TextWatcher = object:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            updateWidgets()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myCycle= MyCycle()
        lifecycle.addObserver(myCycle)

        binding.progressBar.max=4

        binding.editTextTextPersonName.addTextChangedListener(textWatcher)
        binding.editTextPhone.addTextChangedListener(textWatcher)

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->  updateWidgets()}
        binding.checkBoxEURA.setOnClickListener { updateWidgets() }

        binding.buttonApply.setOnClickListener(onApply)
        Log.i("MainActivity", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop")
    }
}




