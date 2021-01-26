package com.ort.mediconsent.presentation.search

sealed class SearchState {
    object SuccessState : SearchState()

    object ErrorState : SearchState()

    object LoadingState : SearchState()
}
