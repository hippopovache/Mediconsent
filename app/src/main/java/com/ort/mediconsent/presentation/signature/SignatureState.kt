package com.ort.mediconsent.presentation.signature

sealed class SignatureState {
    object SuccessState : SignatureState()

    object ErrorState : SignatureState()

    object LoadingState : SignatureState()
}
