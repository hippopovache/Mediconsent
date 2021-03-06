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
import java.io.File

class SignatureViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReponseRepository = MediconsentReponseRepository()

    private val _state = MutableLiveData<SignatureState>()
    private val _reponseState = MutableLiveData<ReponseState>()
    val state: LiveData<SignatureState> get() = _state
    val reponseState: LiveData<ReponseState> get() = _reponseState


    fun sendReponses(reponses: List<Reponse>) {
        _reponseState.value = ReponseState.LoadingState
        viewModelScope.launch {
            try {
                repository.sendReponses(reponses)
                _reponseState.value = ReponseState.SuccessState
            } catch (e: Exception) {
                println("Exception : $e")
                _reponseState.value = ReponseState.ErrorState
            }
        }
    }

    fun sendSignaturePdf(examen: Examen, file: File, bitmap: Bitmap) {
        _state.value = SignatureState.LoadingState
        viewModelScope.launch {
            try {
                repository.sendSignaturePdf(examen, file, bitmap)
                _state.value = SignatureState.SuccessState
            } catch (e: Exception) {
                println("Exception : $e")
                _state.value = SignatureState.ErrorState
            }
        }
    }
}