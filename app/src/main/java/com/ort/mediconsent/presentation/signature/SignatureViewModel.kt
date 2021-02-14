package com.ort.mediconsent.presentation.signature

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentReponseRepository
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.domain.repository.ReponseRepository
import kotlinx.coroutines.launch

class SignatureViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReponseRepository = MediconsentReponseRepository()

    private val _state = MutableLiveData<SignatureState>()
    private val _ReponseState = MutableLiveData<ReponseState>()
    val state: LiveData<SignatureState> get() = _state

    fun sendReponses(reponses: List<Reponse>) {
        _ReponseState.value = ReponseState.LoadingState
        viewModelScope.launch {
            try {
                repository.sendReponses(reponses)
                _ReponseState.value = ReponseState.SuccessState
            } catch (e: Exception) {
                println("Exception : $e")
                _ReponseState.value = ReponseState.ErrorState
            }
        }
    }

    fun sendSignature(examen: Examen, bitmap: Bitmap) {
        _state.value = SignatureState.LoadingState
        viewModelScope.launch {
            try {
                repository.sendSignature(examen, bitmap)
                _state.value = SignatureState.SuccessState
            } catch (e: Exception) {
                println("Exception : $e")
                _state.value = SignatureState.ErrorState
            }
        }
    }
}