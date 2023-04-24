package com.example.zhugpt.bean


data class CompletionRequestBody(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Double,

    //val top_p: Int,
    //val n: Int,
    //val stream: Boolean,
    //val logprobs: Any?,
    //val stop: String
){
    /*constructor(
        model: String,
        prompt: String,
        max_tokens: Int,
        temperature: Int
    ) : this(model, prompt, max_tokens, temperature,1,1,false,null,"\n")*/
}

