package com.jwithana.snapmind

import android.graphics.Bitmap
import com.jwithana.snapmind.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)