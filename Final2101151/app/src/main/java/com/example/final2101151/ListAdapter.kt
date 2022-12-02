package com.example.final2101151

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final2101151.model.MyDatabase
import com.example.final2101151.model.ToDoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ListAdapter :RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(v: View): RecyclerView.ViewHolder(v){
        val textViewList : TextView = v.findViewById(R.id.textViewList)
        val chkButton : Button = v.findViewById(R.id.chkButton)
    }


    private var data = listOf<ToDoList>()
    //항목을 하나씩 넣거나 뺄 경우에는 뮤터블로 만들면 된다.
    //디비를 통해서 넣고 뺸다면 listOf를 사용해라.

    fun setData(data:List<ToDoList>){ //data가 변경될 경우 이런식으로 노티파이를 해줘야 새로그려준다.
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //이 메소드 안에 있는 코드들은 항상 똑같다 외워라.
        //클래스명, 아이템레이아웃 명만 바꾸면된다.
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ListViewHolder(v)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val todolist= data[position] //레코드 데이터를 부른다.
        holder.textViewList.text = todolist.content
        // 부른 데이터의 시간과 음식을 홀더 시간 음식에 저장한다.
        holder.chkButton.setOnClickListener{
            it.visibility = View.INVISIBLE
        }
    }
    override fun getItemCount(): Int { //애도 코드가 거이 똑같다 data.size를 리턴해주면 됨. (리스트항목값리턴)
        return data.size
    }


}

