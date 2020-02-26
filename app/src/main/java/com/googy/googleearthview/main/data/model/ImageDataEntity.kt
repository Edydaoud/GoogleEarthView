package com.googy.googleearthview.main.data.model

import com.googy.googleearthview.base.data.model.BaseDataEntity
import com.googy.googleearthview.main.domain.model.ImageDataDomainEntity
import com.googy.googleearthview.main.presentation.model.ImageDataUiEntity

data class ImageDataEntity(
    val attribution: String,
    val country: String,
    val earthLink: String,
    val earthLinkTitle: String,
    val hue: Int,
    val id: String,
    val lat: Double,
    val lng: Double,
    val mapsLink: String,
    val mapsLinkTitle: String,
    val name: String,
    val nextSlug: String,
    val photoUrl: String,
    val prevSlug: String,
    val primaryColor: List<Int>,
    val region: String,
    val shareUrl: String,
    val slug: String,
    val thumbUrl: String,
    val title: String
) : BaseDataEntity<ImageDataUiEntity, ImageDataDomainEntity>() {
    override fun toDomainEntity(): ImageDataDomainEntity {
        return ImageDataDomainEntity(
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