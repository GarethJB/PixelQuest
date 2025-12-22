package com.jb.pixelquest.data.remote.android

import com.jb.pixelquest.data.remote.android.api.PixelQuestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 설정 및 API 인스턴스 생성
 * 
 * 실제 프로젝트에서는 baseUrl을 환경 변수나 설정 파일에서 가져와야 합니다.
 */
object RetrofitModule {
    
    private const val BASE_URL = "https://api.pixelquest.com/" // TODO: 실제 API URL로 변경
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val pixelQuestApi: PixelQuestApi = retrofit.create(PixelQuestApi::class.java)
}
