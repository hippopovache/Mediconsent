@file:Suppress("unused", "unused", "unused")

package com.ort.mediconsent.presentation.connect

import com.ort.mediconsent.domain.model.Utilisateur

sealed class ConnectState {
    class SuccessState(val utilisateur: Utilisateur) : ConnectState()

    object ErrorState : ConnectState()

    object LoadingState : ConnectState()
}
