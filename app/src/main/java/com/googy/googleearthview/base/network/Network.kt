/*
 * Created by Edd on 3/19/19 1:07 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 3/19/19 12:32 PM by Edd
 */

package com.googy.googleearthview.base.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://earthview.withgoogle.com/_api/"
private const val CONNECT_TIMEOUT = 30000L
private const val READ_TIMEOUT = 30000L
private const val WRITE_TIMEOUT = 90000L

fun provideOkHttpClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
    return httpClientBuilder.build()
}

fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(provideGsonConverterFactory())
        .build()

}

fun provideGsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(getGson())
}

private fun getGson(): Gson {
    return GsonBuilder().create()
}


