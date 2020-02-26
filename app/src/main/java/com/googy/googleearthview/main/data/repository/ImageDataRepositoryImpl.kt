package com.googy.googleearthview.main.data.repository

import com.googy.googleearthview.base.domain.model.EitherErrorOr
import com.googy.googleearthview.base.domain.repository.safeApiCall
import com.googy.googleearthview.main.data.service.ImageDataApiService
import com.googy.googleearthview.main.domain.model.ImageDataDomainEntity
import com.googy.googleearthview.main.domain.repository.ImageDataRepository


class ImageDataRepositoryImpl(
    private val imageDataApiService: ImageDataApiService
) : ImageDataRepository {
    override suspend fun getImageData(imageId: String): EitherErrorOr<ImageDataDomainEntity> {
        return safeApiCall(imageDataApiService.getImageData(imageId)) {
            it.toDomainEntity()
        }
    }
}