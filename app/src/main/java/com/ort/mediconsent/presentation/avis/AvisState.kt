package com.ort.mediconsent.presentation.avis

sealed class AvisState {
    object SuccessState : AvisState()

    object ErrorState : AvisState()

    object LoadingState : AvisState()
}
