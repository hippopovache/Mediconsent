package com.ort.mediconsent.presentation.rdvList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentUserRepository
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.UserRepository
import kotlinx.coroutines.launch

class RdvListViewModel(application: Application) : AndroidViewModel(application) {
    val repository: UserRepository = MediconsentUserRepository()

    private val _state = MutableLiveData<RdvListState>()
    val state: LiveData<RdvListState> get() = _state

    fun getUserRdvForToday(firstname: String, name: String): Examen? {
        var examen: Examen? = null
        _state.value = RdvListState.LoadingState
        viewModelScope.launch {
            try {
                _state.value = RdvListState.SuccessState
            } catch (e: Exception) {
                _state.value = RdvListState.ErrorState
            }
        }
        return examen
    }
}