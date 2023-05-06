package com.example.zhugpt.net

import android.util.Log
import com.example.zhugpt.bean.*
import com.example.zhugpt.constant.UrlConstant
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.*


class NetInfoPresenter {
    companion object{
        private lateinit var mInstance : NetInfoPresenter
        private lateinit var apiService : OpenAiApi
        init {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .build()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(UrlConstant.OPEN_API_URI)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            apiService = retrofit.create(OpenAiApi::class.java)
        }

        fun getInstance() : NetInfoPresenter{
            if (!this::mInstance.isInitialized){
                mInstance = NetInfoPresenter()
            }
            return mInstance
        }
    }
    fun getApiModels(feedBack : NetFeedBack){
        val authHeaderValue = "Bearer ${UrlConstant.MY_API_KEY}"
        apiService.getModels(authHeaderValue)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Response<ModelApiObject>?>{
                override fun onSubscribe(d: Disposable) {
                    var a = ""
                }

                override fun onNext(t: Response<ModelApiObject>) {
                    var models = t.body()
                    feedBack.doSuccess(models)
                    Log.i("ceshi1", t.toString())
                }

                override fun onError(e: Throwable) {
                    // 在这里处理获取数据时发生的错误
                    e.message?.let { Log.e("ceshi", it) }
                    if (e is HttpException) {
                        val responseBody = e.response().errorBody()
                        val responseString = responseBody?.string()
                        if (responseString != null) {
                            Log.e("ceshi", responseString)
                        }
                    }
                }

                override fun onComplete() {
                    var a = ""
                }

            })
    }

    fun functionCompletion(feedBack: NetFeedBack, ask : String){
        val request = CompletionRequestBody(
            "text-davinci-003",
            ask,700,0.5)



        apiService.getCompletion(
            "Bearer ${UrlConstant.MY_API_KEY}",
            request
        )?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Response<CompletionResponse>?> {
                override fun onSubscribe(d: Disposable) {
                    // 进行订阅前的处理
                    var aa = ""
                }

                override fun onError(e: Throwable) {
                    // 处理请求错误
                    var aa = ""
                }

                override fun onNext(t: Response<CompletionResponse>) {
                    var chatResponse: CompletionResponse? = t.body() ?: return
                    // 在这里处理API响应
                    feedBack.doSuccess(chatResponse)
                }

                override fun onComplete() {
                }
            })
    }


    fun functionChat(sessionId : String, messages : ArrayList<Message>, feedBack: NetFeedBack){
        val request = ChatRequestBody(
            "gpt-3.5-turbo",
            messages)

        if("" == sessionId) {val request = ChatRequestBody(
            "gpt-3.5-turbo",
            messages)}

        apiService.getChatCompletion(
            "Bearer ${UrlConstant.MY_API_KEY}",
            request
        )?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Response<ChatResponse>?> {
                override fun onSubscribe(d: Disposable) {
                    // 进行订阅前的处理
                }

                override fun onError(e: Throwable) {
                    // 处理请求错误
                }

                override fun onNext(t: Response<ChatResponse>) {
                    var chatResponse: ChatResponse? = t.body() ?: return
                    // 在这里处理API响应
                    feedBack.doSuccess(chatResponse)
                }

                override fun onComplete() {
                }
            })
    }


    fun functionEdit(myText: String, instruction: String, feedBack: NetFeedBack){
        var myInstruction = instruction
        if(instruction == "" || instruction == null){
            myInstruction = "Fix the spelling mistakes"
        }
        val request = EditRequestBody(
            "text-davinci-edit-001",
            myText,
            myInstruction
        )

        apiService.getEdit(
            "Bearer ${UrlConstant.MY_API_KEY}",
            request
        )?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Response<EditResponse>?> {
                override fun onSubscribe(d: Disposable) {
                    // 进行订阅前的处理
                }

                override fun onError(e: Throwable) {
                    // 处理请求错误
                }

                override fun onNext(t: Response<EditResponse>) {
                    var editResponse: EditResponse? = t.body() ?: return
                    // 在这里处理API响应
                    feedBack.doSuccess(editResponse)
                }

                override fun onComplete() {
                }
            })
    }


    abstract class NetFeedBack{
        open fun doSuccess(`object`: Any?){}
        open fun doFail(){}
    }
}