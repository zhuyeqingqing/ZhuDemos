package com.example.zhugpt.bean

data class CompletionResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage
) {
    data class Choice(
        val index: Int,
        val text: String,
        val logprobs: Int,
        val finish_reason: String
    )

    data class Usage(
        val prompt_tokens: Int,
        val completion_tokens: Int,
        val total_tokens: Int
    )
}
