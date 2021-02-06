package com.ort.mediconsent.presentation.search

import com.ort.mediconsent.domain.model.Examen

sealed class SearchState {
    class SuccessState(val examen: Examen) : SearchState()

    object ErrorState : SearchState()

    object LoadingState : SearchState()
}
