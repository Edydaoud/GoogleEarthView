package com.googy.googleearthview.main.presentation.model

import com.googy.googleearthview.base.presentation.model.BaseUIEntity

data class ImageDataUiEntity(
    val id: String,
    val name: String,
    val nextSlug: String,
    val photoUrl: String,
    val prevSlug: String,
    val primaryColor: List<Int>,
    val shareUrl: String,
    val thumbUrl: String
) : BaseUIEntity()