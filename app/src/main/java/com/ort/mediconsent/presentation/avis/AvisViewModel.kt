package com.ort.mediconsent.presentation.avis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentAvisRepository
import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.AvisRepository
import kotlinx.coroutines.launch

class AvisViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AvisRepository = MediconsentAvisRepository()

    private val _state = MutableLiveData<AvisState>()
    val state: LiveData<AvisState> get() = _state

    fun sendAvis(examen: Examen, avis: Avis) {
        _state.value = AvisState.LoadingState
        viewModelScope.launch {
            try {
                repository.sendAvis(examen, avis)
                _state.value = AvisState.SuccessState
            } catch (e: Exception) {
                println("Exception : $e")
                _state.value = AvisState.ErrorState
            }
        }
    }
}