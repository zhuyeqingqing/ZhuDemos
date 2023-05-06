package com.example.zhugpt.net

import com.example.zhugpt.bean.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface OpenAiApi {
    @GET("v1/models")
    fun getModels(@Header("Authorization") authorization: String?): Observable<Response<ModelApiObject>?>?


    @POST("v1/completions")
    fun getCompletion(
        @Header("Authorization") authorization: String?,
        @Body request: CompletionRequestBody?
    ): Observable<Response<CompletionResponse>?>

    @POST("v1/chat/completions")
    fun getChatCompletion(
        @Header("Authorization") authorization: String?,
        @Body request: ChatRequestBody?
    ): Observable<Response<ChatResponse>?>

    @POST("v1/edits")
    fun getEdit(
        @Header("Authorization") authorization: String?,
        @Body request: EditRequestBody?
    ): Observable<Response<EditResponse>?>

    @POST("v1/images/generations")
    fun getImageGenerate(
        @Header("Authorization") authorization: String?,
        @Body request: ImageGenerateRequestBody?
    ): Observable<Response<ImageResponse>?>
}