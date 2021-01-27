package com.ort.mediconsent.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    //private val repository: MovieRepository = OmdbRepository()

    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> get() = _state

    /*fun getMovieDetail(id: String) {
        _state.value = ConnectState.LoadingState

        viewModelScope.launch {
            try {
                _state.value = ConnectState.SuccessState
            } catch (e: Exception) {
                _state.value = ConnectState.ErrorState
            }
        }
    }*/
}