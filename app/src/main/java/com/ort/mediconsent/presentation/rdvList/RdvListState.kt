package com.ort.mediconsent.presentation.rdvList

sealed class RdvListState {
    object SuccessState : RdvListState()

    object ErrorState : RdvListState()

    object LoadingState : RdvListState()
}
