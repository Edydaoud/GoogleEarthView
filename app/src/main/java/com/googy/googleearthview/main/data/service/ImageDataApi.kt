package com.googy.googleearthview.main.data.service

import com.googy.googleearthview.main.data.model.ImageDataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageDataApi {
    @GET("{imageId}.json")
    fun getImageData(
        @Path("imageId") imageId: String
    ): Call<ImageDataEntity>
}