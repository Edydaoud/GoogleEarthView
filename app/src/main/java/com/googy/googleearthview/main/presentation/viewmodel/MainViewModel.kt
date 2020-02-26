package com.googy.googleearthview.main.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.googy.googleearthview.base.presentation.viewmodel.BaseViewModel
import com.googy.googleearthview.main.domain.usecase.GetImageDataUseCase

class MainViewModel(
    private val getImageDataUseCase: GetImageDataUseCase
) : BaseViewModel<MainViewModel.State, MainViewModel.Command>(State()) {

    sealed class Command : BaseCommand() {
        data class ImageReady(
            val imageUrl: String,
            val thumbUrl: String,
            val name: String,
            val shareUrl: String,
            val primaryColor: List<Int>
        ) : Command()
    }

    fun getImage(imageId: String = getState().currentId) {
        getImageDataUseCase(
            GetImageDataUseCase.Params(imageId),
            parentScope = viewModelScope,
            onSuccess = {
                setState { copy(previousId = it.prevSlug, currentId = it.id, nextId = it.nextSlug) }
                postCommand(
                    Command.ImageReady(
                        it.photoUrl,
                        it.thumbUrl,
                        it.name,
                        it.shareUrl,
                        it.primaryColor
                    )
                )
            },
            onFailure = {
                //TODO handle errors
            })
    }

    fun nextImage() {
        getImage(getState().nextId)
    }

    fun previousImage() {
        getImage(getState().previousId)
    }

    data class State(
        val previousId: String = "",
        val currentId: String = "14339",
        val nextId: String = ""
    )

    override val commandLiveData: MutableLiveData<Command> = MutableLiveData()
}