package com.googy.googleearthview.main.domain.repository

import com.googy.googleearthview.base.domain.model.EitherErrorOr
import com.googy.googleearthview.base.domain.repository.BaseRepository
import com.googy.googleearthview.main.domain.model.ImageDataDomainEntity

interface ImageDataRepository : BaseRepository {
    suspend fun getImageData(imageId: String): EitherErrorOr<ImageDataDomainEntity>
}