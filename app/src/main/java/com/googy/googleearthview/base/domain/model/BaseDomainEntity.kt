package com.googy.googleearthview.base.domain.model

import com.googy.googleearthview.base.presentation.model.BaseUIEntity
import java.io.Serializable

abstract class BaseDomainEntity<out T : BaseUIEntity> : Serializable {
    abstract fun toUiEntity(): T
}

