package com.ort.mediconsent.presentation.connect

sealed class ConnectState {
    class SuccessState(val isConnected: Boolean) : ConnectState()

    object ErrorState : ConnectState()

    object LoadingState : ConnectState()
}
