package com.ort.mediconsent.presentation.rdvList

import com.ort.mediconsent.domain.model.Examen

sealed class RdvListState {
    class SuccessState(val examen: Examen) : RdvListState()

    object ErrorState : RdvListState()

    object LoadingState : RdvListState()
}
