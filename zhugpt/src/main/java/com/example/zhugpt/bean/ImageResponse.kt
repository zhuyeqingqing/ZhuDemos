package com.example.zhugpt.bean

data class ImageResponse(
    val created: Long,
    val data: List<GptImage>,
) {
    data class GptImage(
        val url: String,
    )
}
