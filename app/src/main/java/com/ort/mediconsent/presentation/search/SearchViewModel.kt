package com.ort.mediconsent.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.mock.MockUserRepository
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.UserRepository
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    val repository: UserRepository = MockUserRepository()

    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> get() = _state

    fun getUserRdvForToday(firstname: String, name: String): Examen? {
        var examen: Examen? = null
        _state.value = SearchState.LoadingState
        viewModelScope.launch {
            try {
                examen = repository.getUserRdvForToday(firstname, name)
                _state.value = SearchState.SuccessState(examen!!)
            } catch (e: Exception) {
                _state.value = SearchState.ErrorState
            }
        }
        return examen
    }
}