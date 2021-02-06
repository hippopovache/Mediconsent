package com.ort.mediconsent.presentation.connect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentUserRepository
import com.ort.mediconsent.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ConnectViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = MediconsentUserRepository()

    private val _state = MutableLiveData<ConnectState>()

    val state: LiveData<ConnectState> get() = _state

    fun getUserConnect(nom_utilisateur: String, password: String) {
        _state.value = ConnectState.LoadingState
        viewModelScope.launch {
            try {
                _state.value =
                    ConnectState.SuccessState(repository.getUserConnect(nom_utilisateur, password))
            } catch (e: Exception) {
                println(e)
                _state.value = ConnectState.ErrorState
            }
        }
    }
}