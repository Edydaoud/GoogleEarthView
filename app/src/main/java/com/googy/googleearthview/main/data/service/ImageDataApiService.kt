package com.googy.googleearthview.main.data.service

import com.googy.googleearthview.main.data.model.ImageDataEntity
import retrofit2.Call
import retrofit2.Retrofit

class ImageDataApiService(private val retrofit: Retrofit) : ImageDataApi {

    private val imageDataApi by lazy { retrofit.create(ImageDataApi::class.java) }

    override fun getImageData(imageId: String): Call<ImageDataEntity> {
        return imageDataApi.getImageData(imageId)
    }

}