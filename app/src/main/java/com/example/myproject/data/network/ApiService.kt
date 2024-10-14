package com.example.myproject.data.network

import com.example.myproject.data.model.CategoriesRequest
import com.example.myproject.data.model.HomeResponse
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.data.model.LoginResponse
import com.example.myproject.data.model.RegisterRequest
import com.example.myproject.data.model.RegisterResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization")
        token: String, @Header("lang") lang: String
    ): Response<CategoriesRequest>

    @GET("home")
    suspend fun getHomeData(
        @Header("Authorization")
        token: String, @Header("lang") lang: String,
    ): Response<HomeResponse>

}


object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        // Those 3 lines for this shitty slow Api
        .callTimeout(0, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(0, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(0, java.util.concurrent.TimeUnit.SECONDS)
        .build()


    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://student.valuxapps.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}