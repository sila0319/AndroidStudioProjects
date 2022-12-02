package com.example.listpractice.util

import com.example.listpractice.R
import kotlin.random.Random

data class ChatRoomInfo(val image:Int, val name:String, val time:String)

class DataGenerator{
    companion object{
        @JvmField
        val images = arrayOf(
            R.drawable.ic_123, R.drawable.ic_football, R.drawable.ic_ac_unit,
            R.drawable.ic_battery, R.drawable.ic_emotions, R.drawable.ic_spa
        )
        @JvmField
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        @JvmStatic
        fun get():MutableList<ChatRoomInfo>{
            val count = Random.nextInt(1, 100)
            val list = MutableList(count){x
                val imageIndex = Random.nextInt(images.size)
                val nameLength = Random.nextInt(3, 10)
                val randomString = (1..nameLength)
                    .map { charPool[Random.nextInt(0, charPool.size)] }
                    .joinToString("")
                val randomDay = Random.nextInt(1, 32)
                ChatRoomInfo(images[imageIndex], randomString, "3월 ${randomDay}일")
            }
            return list
        }
    }
}