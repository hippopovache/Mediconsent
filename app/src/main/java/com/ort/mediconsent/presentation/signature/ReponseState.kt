package com.ort.mediconsent.presentation.signature

sealed class ReponseState {
    object SuccessState : ReponseState()

    object ErrorState : ReponseState()

    object LoadingState : ReponseState()
}
