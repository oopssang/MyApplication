package com.oopssang.book.api.service

import com.oopssang.book.data.SearchBookResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchBookService {

    @GET("v3/search/book")
    suspend fun searchBook(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("target") target: String
        ): SearchBookResponse

    companion object {
        private const val BASE_URL = "https://dapi.kakao.com/"

        fun create(): SearchBookService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

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
