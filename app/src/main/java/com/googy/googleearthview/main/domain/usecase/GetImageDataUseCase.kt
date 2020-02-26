package com.googy.googleearthview.main.domain.usecase

import com.googy.googleearthview.base.domain.model.EitherErrorOr
import com.googy.googleearthview.base.domain.usecase.EitherUserCaseFolded
import com.googy.googleearthview.main.domain.repository.ImageDataRepository
import com.googy.googleearthview.main.presentation.model.ImageDataUiEntity

class GetImageDataUseCase(
    private val imageDataRepository: ImageDataRepository
) : EitherUserCaseFolded<ImageDataUiEntity, GetImageDataUseCase.Params>() {

    data class Params(val imageId: String)

    override suspend fun run(params: Params): EitherErrorOr<ImageDataUiEntity> {
        return imageDataRepository.getImageData(params.imageId).map { it.toUiEntity() }
    }
}