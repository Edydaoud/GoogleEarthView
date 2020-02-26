package com.googy.googleearthview.main.domain.model

import com.googy.googleearthview.base.domain.model.BaseDomainEntity
import com.googy.googleearthview.main.presentation.model.ImageDataUiEntity

data class ImageDataDomainEntity(
    val id: String,
    val name: String,
    val nextSlug: String,
    val photoUrl: String,
    val prevSlug: String,
    val primaryColor: List<Int>,
    val shareUrl: String,
    val thumbUrl: String
) : BaseDomainEntity<ImageDataUiEntity>() {
    override fun toUiEntity(): ImageDataUiEntity {
        return ImageDataUiEntity(
            id,
            name,
            nextSlug,
            photoUrl,
            prevSlug,
            primaryColor,
            shareUrl,
            thumbUrl
        )
    }
}