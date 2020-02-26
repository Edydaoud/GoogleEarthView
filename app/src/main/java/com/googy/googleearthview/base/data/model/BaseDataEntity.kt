package com.googy.googleearthview.base.data.model

import com.googy.googleearthview.base.domain.model.BaseDomainEntity
import com.googy.googleearthview.base.presentation.model.BaseUIEntity

abstract class BaseDataEntity<out S : BaseUIEntity, out T : BaseDomainEntity<S>> {
    abstract fun toDomainEntity(): T
}
