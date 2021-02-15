package com.ort.mediconsent.presentation.rdvList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentExamenRepository
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.ExamenRepository
import kotlinx.coroutines.launch

class RdvListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamenRepository = MediconsentExamenRepository()

    private val _state = MutableLiveData<RdvListState>()
    val state: LiveData<RdvListState> get() = _state

    fun getExamenDetails(id: Int): Examen? {
        val examen: Examen? = null
        _state.value = RdvListState.LoadingState
        viewModelScope.launch {
            try {
                _state.value = RdvListState.SuccessState(repository.getExamenById(id))
            } catch (e: Exception) {
                _state.value = RdvListState.ErrorState
            }
        }
        return examen
    }
}