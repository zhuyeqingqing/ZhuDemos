package com.example.zhugpt.bean

data class ChatRequestBody(
    var model: String,
    var messages: List<Message>
)
data class Message(
    var role: String,
    var content: String
)

