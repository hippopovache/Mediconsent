package com.ort.mediconsent.presentation.question

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ort.mediconsent.data.repository.MediconsentQuestionRepository
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: QuestionRepository = MediconsentQuestionRepository()

    private val _state = MutableLiveData<QuestionState>()
    val state: LiveData<QuestionState> get() = _state

    fun getExamenQuestions(idFormulaire: Int): List<Question>? {
        var questions: List<Question>? = null
        _state.value = QuestionState.LoadingState
        viewModelScope.launch {
            try {
                _state.value =
                    QuestionState.SuccessState(repository.getExamenQuestions(idFormulaire))
            } catch (e: Exception) {
                println("Exception : $e")
                _state.value = QuestionState.ErrorState
            }
        }
        return questions
    }
}