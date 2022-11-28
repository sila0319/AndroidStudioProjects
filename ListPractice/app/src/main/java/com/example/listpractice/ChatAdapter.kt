package com.example.listpractice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listpractice.util.ChatRoomInfo
import java.util.*

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(v: View, listener: OnListItemClickListener?):RecyclerView.ViewHolder(v){
        val textViewName: TextView = v.findViewById(R.id.textViewName)
        val textViewTime:TextView = v.findViewById(R.id.textViewTime)
        val imageView:ImageView = v.findViewById(R.id.imageView)
        init{
            v.setOnClickListener {
                Log.d("List", "$layoutPosition clicked")
                listener?.onItemClick(layoutPosition)
            }
        }
    }

    fun interface OnListItemClickListener {
        fun onItemClick(position:Int)
    }

    private var listener:OnListItemClickListener? = null
    private var data = mutableListOf<ChatRoomInfo>()

    fun setItemClickListener(listener:OnListItemClickListener){
        this.listener = listener
    }

    fun setData(data:MutableList<ChatRoomInfo>){
        this.data = data
        notifyDataSetChanged()
    }

    fun swapItem(from:Int, to:Int){
        Collections.swap(data, from, to)
        notifyItemMoved(from, to)
    }

    fun removeItem(index:Int){
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun getItem(index:Int):ChatRoomInfo? {
        return if(index in 0 ..data.lastIndex)
            data[index]
        else null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = data[position]

        holder.imageView.setImageResource(item.image)
        holder.textViewName.text = item.name
        holder.textViewTime.text = item.time
    }

    override fun getItemCount(): Int {
        return data.size
    }
}