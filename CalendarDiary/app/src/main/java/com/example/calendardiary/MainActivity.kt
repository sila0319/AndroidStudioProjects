package com.example.calendardiary

import android.annotation.SuppressLint
import android.app.Activity
import kotlinx.coroutines.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.calendardiary.databinding.ActivityMainBinding

import com.example.calendardiary.model.*


class MainActivity : AppCompatActivity() , View.OnClickListener {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //달력의 변화가 감지되었을때 다른요일을 클릭했을 경우
        binding.calendarView.setOnDateChangeListener{ view,year,month,dayOfMonth ->
            resetText()
            var date = String.format("%d / %d /%d",year, month+1,dayOfMonth)
            binding.diaryTextView.text = date
            binding.diaryTextView.visibility = View.VISIBLE
            chkDay(date)
            offTextView()
            onSaveButton()
        }
        //삭제버튼을 눌렀을떄
        binding.deleteButton.setOnClickListener{
            onSaveButton()
            offTextView()
            resetText()
            //디비를 통해 해당 날짜 삭제
            var date = binding.diaryTextView.text.toString()
            var title = binding.diaryTitleTextView.text.toString()
            var content = binding.diaryContentTextView.text.toString()
            deleteDay(date,title,content)
        }

        //저장버튼을 눌렀을 때
        binding.saveButton.setOnClickListener{
            offSaveButton()
            binding.diaryTitleTextView.setText(binding.editDiaryTitleTextView.text)
            binding.diaryContentTextView.setText(binding.editDiaryContentTextView.text)
            onTextView()
            //디비를 통해 해당 날짜 수정
            var date = binding.diaryTextView.text.toString()
            var title = binding.diaryTitleTextView.text.toString()
            var content = binding.diaryContentTextView.text.toString()
            insertDay(date,title,content)

        }

        //수정버튼을 눌렀을때
        binding.updateButton.setOnClickListener{
            onSaveButton()
            binding.editDiaryTitleTextView.setText(binding.diaryTitleTextView.text)
            binding.editDiaryContentTextView.setText(binding.diaryContentTextView.text)
            offTextView()
            var date = binding.diaryTextView.text.toString()
            var title = binding.diaryTitleTextView.text.toString()
            var content = binding.diaryContentTextView.text.toString()
            insertDay(date,title,content)
        }

    }
    fun offSaveButton(){ //버튼들은 반대로 움직이므로 Save를 나타내냐 안나타내냐로 구분한다.
        binding.saveButton.visibility = View.INVISIBLE //저장버튼 안 나타내기
        binding.deleteButton.visibility = View.VISIBLE //삭제버튼 보이기
        binding.updateButton.visibility = View.VISIBLE //수정버튼 보이기
    }
    fun onSaveButton(){
        binding.saveButton.visibility = View.VISIBLE //저장버튼  나타내기
        binding.deleteButton.visibility = View.INVISIBLE //삭제버튼 보이기
        binding.updateButton.visibility = View.INVISIBLE //수정버튼 보이기
    }

    fun onTextView(){ //TextView들을 보이게하면 edit화면들은 안보이게 해줘야한다.
        binding.diaryTitleTextView.visibility = View.VISIBLE //일기제목보이기
        binding.diaryContentTextView.visibility = View.VISIBLE //일기내용보이기
        binding.editDiaryTitleTextView.visibility = View.INVISIBLE //일기제목수정안보이기
        binding.editDiaryContentTextView.visibility = View.INVISIBLE //일기내용수정 안보이기
    }
    fun offTextView(){
        binding.diaryTitleTextView.visibility = View.INVISIBLE //일기제목 안보이기
        binding.diaryContentTextView.visibility = View.INVISIBLE //일기내용 안보이기
        binding.editDiaryTitleTextView.visibility = View.VISIBLE //일기제목수정안보이기
        binding.editDiaryContentTextView.visibility = View.VISIBLE //일기내용수정 안보이기
    }
    fun resetText(){ //text 초기값으로 복구
        binding.diaryTitleTextView.text=("") //제목텍스트 내용 없애기
        binding.diaryContentTextView.text=("") //내용텍스트 내용 없애기
        binding.editDiaryTitleTextView.setText("")
        binding.editDiaryContentTextView.setText("")
        binding.editDiaryTitleTextView.hint = "일기 제목을 입력해주세요" //힌트 재설정
        binding.editDiaryContentTextView.hint= "일기내용을 입력해주세요" // 힌트 재설정
    }

    fun chkDay (date : String){
        CoroutineScope(Dispatchers.IO).launch {
            val db = MyDatabase.getInstance(this@MainActivity)
            val list = db?.myDao()?.select(date)
            Log.d("List" , list.toString())
                list?.forEach{
                    if(it?.date.equals(date)){
                        Log.d("List" , "데이터가 존재합니다.")
                        CoroutineScope(Dispatchers.Main).launch {

                                offSaveButton()
                                if(it?.title==null){
                                    binding.diaryTitleTextView.text = ""
                                }else{
                                    binding.diaryTitleTextView.text = it?.title.toString()
                                }
                                if(it.content==null){
                                    binding.diaryContentTextView.text = ""
                                }else{
                                    binding.diaryContentTextView.text = it?.content.toString()
                                }
                                binding.diaryTitleTextView.text = it?.title
                                binding.diaryContentTextView.text = it?.content
                                binding.diaryTextView.visibility = View.VISIBLE  //날짜 보이기
                                onTextView()
                            }
                    }else{
                        CoroutineScope(Dispatchers.Main).launch {
                            onSaveButton()
                            resetText()
                            offTextView()
                        }

                    }
                }
        }
    }
    fun insertDay(date:String,title:String, content:String){
        CoroutineScope(Dispatchers.IO).launch {
            val db = MyDatabase.getInstance(this@MainActivity)
            db?.myDao()?.insert(MyDiary(date,title,content ))
        }
    }

    fun deleteDay(date:String,title:String, content:String){
        CoroutineScope(Dispatchers.IO).launch {
            val db = MyDatabase.getInstance(this@MainActivity)
            db?.myDao()?.delete(MyDiary(date,title,content))
            CoroutineScope(Dispatchers.Main).launch {
                onSaveButton()
                resetText()
                offTextView()
            }
        }
    }

    override fun onClick(v: View?) {

    }
    }

