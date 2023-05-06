package com.example.zhugpt.bean

data class ImageGenerateRequestBody(
    val prompt: String,
    val n: Int = 2,
    val size: String
)

