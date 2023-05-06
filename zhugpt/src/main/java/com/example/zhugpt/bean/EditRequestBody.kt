package com.example.zhugpt.bean

data class EditRequestBody(
    val model: String,
    val input: String,
    val instruction: String
)

