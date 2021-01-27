package com.ort.mediconsent.presentation.search

import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.presentation.connect.ConnectState

sealed class SearchState {
    class SuccessState(val examen: Examen) : SearchState()

    object ErrorState : SearchState()

    object LoadingState : SearchState()
}
