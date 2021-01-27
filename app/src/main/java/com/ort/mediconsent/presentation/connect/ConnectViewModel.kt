package com.ort.mediconsent.presentation.connect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.mock.MockUserRepository
import com.ort.mediconsent.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ConnectViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = MockUserRepository()

    private val _state = MutableLiveData<ConnectState>()

    val state: LiveData<ConnectState> get() = _state

    fun getUserConnect(login: String, password: String): Boolean {
        var isConnected = false
        _state.value = ConnectState.LoadingState
        viewModelScope.launch {
            try {
                isConnected = repository.getUserConnect(login, password)
                _state.value = ConnectState.SuccessState(isConnected)
            } catch (e: Exception) {
                _state.value = ConnectState.ErrorState
                isConnected = false
            }
        }
        return isConnected
    }
}