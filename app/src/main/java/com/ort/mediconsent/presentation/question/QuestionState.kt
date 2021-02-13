package com.ort.mediconsent.presentation.question

import com.ort.mediconsent.domain.model.Question

sealed class QuestionState {
    class SuccessState(val question: List<Question>) : QuestionState()

    object ErrorState : QuestionState()

    object LoadingState : QuestionState()
}
