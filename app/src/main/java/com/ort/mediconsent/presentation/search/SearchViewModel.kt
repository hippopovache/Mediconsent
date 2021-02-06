package com.ort.mediconsent.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentExamenRepository
import com.ort.mediconsent.domain.repository.ExamenRepository
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamenRepository = MediconsentExamenRepository()

    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> get() = _state

    fun getUserRdvForToday(firstname: String, name: String) {
        _state.value = SearchState.LoadingState
        viewModelScope.launch {
            try {
                println("yo")
                _state.value =
                    SearchState.SuccessState(repository.getUserRdvForToday(firstname, name))
            } catch (e: Exception) {
                println("error$e")
                _state.value = SearchState.ErrorState
            }
        }
    }
}