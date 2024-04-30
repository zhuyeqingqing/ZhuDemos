package com.example.zhugpt.net

import com.example.zhugpt.bean.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Multipart
    @POST("v1/images/edits")
    fun createImageEdit(
        @Header("Authorization") apiKey: String,
        @Part image: MultipartBody.Part,
        @Part mask: MultipartBody.Part?,
        @Part("prompt") prompt: RequestBody,
        @Part("n") n: RequestBody,
        @Part("size") size: RequestBody
    ): Observable<Response<ImageResponse>>
}