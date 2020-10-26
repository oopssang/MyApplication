package com.oopssang.book.api.service

import com.oopssang.book.data.SearchBookResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchBookService {

    @GET("v3/search/book")
    suspend fun searchBook(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") authorization: String = "KakaoAK 0cb9baec6026922e4ae87354751a2605",
        @Query("sort") sort: String = "accuracy"
        ): SearchBookResponse

    companion object {
        private const val BASE_URL = "https://dapi.kakao.com/"

        fun create(): SearchBookService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchBookService::class.java)
        }
    }
}
