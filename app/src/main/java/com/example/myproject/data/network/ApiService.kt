package com.example.myproject.data.network

import com.example.myproject.data.model.CategoriesRequest
import com.example.myproject.data.model.HomeResponse
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.data.model.LoginResponse
import com.example.myproject.data.model.RegisterRequest
import com.example.myproject.data.model.RegisterResponse
import com.example.myproject.data.model.cart.AddOrDeleteCartRequest
import com.example.myproject.data.model.cart.AddorDeleteCartResponse
import com.example.myproject.data.model.cart.CartResponse
import com.example.myproject.data.model.favourite.AddOrDeleteFavouriteRequest
import com.example.myproject.data.model.favourite.AddOrDeleteResponse
import com.example.myproject.data.model.favourite.FavouritesResponse
import com.example.myproject.data.model.search.SearchRequest
import com.example.myproject.data.model.search.SearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("categories")
    suspend fun getCategories(
        @Header("Authorization")
        token: String,
        @Header("lang") lang: String,
    ): Response<CategoriesRequest>

    @GET("home")
    suspend fun getHomeData(
        @Header("Authorization")
        token: String,
        @Header("lang") lang: String,
    ): Response<HomeResponse>

    //get favourites
    @GET("favorites")
    suspend fun getFavouritesData(
        @Header("Authorization") token: String,
        @Header("lang") lang: String,
    ): Response<FavouritesResponse>

    //    add or delete favourites
    @POST("favorites")
    suspend fun addOrDeleteFavourites(
        @Header("Authorization") token: String,
        @Header("lang") lang: String = "en",
        @Body addOrDeleteFavouriteRequest: AddOrDeleteFavouriteRequest,
    ): Response<AddOrDeleteResponse>

    //    get cart products
    @GET("carts")
    suspend fun getCartProducts(
        @Header("Authorization") token: String,
        @Header("lang") lang: String,
    ): Response<CartResponse>

    //    add or delete cart
    @POST("carts")
    suspend fun addOrDeleteCart(
        @Header("Authorization") token: String,
        @Header("lang") lang: String = "en",
        @Body addOrDeleteCartRequest: AddOrDeleteCartRequest,

        ): Response<AddorDeleteCartResponse>

    //search
    @POST("products/search")
    suspend fun searchProducts(
        @Header("Authorization")
        token: String,
        @Header("lang") lang: String,
        @Body body: SearchRequest,
    ): Response<SearchResponse>


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