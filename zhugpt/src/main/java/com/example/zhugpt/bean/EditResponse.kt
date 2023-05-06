package com.example.zhugpt.bean

data class EditResponse(
    val `object`: String,
    val created: Long,
    val choices: List<Choice>,
    val usage: Usage
) {
    data class Choice(
        val index: Int,
        val text: String,
    )

    data class Usage(
        val prompt_tokens: Int,
        val completion_tokens: Int,
        val total_tokens: Int
    )
}
