package com.ort.mediconsent.domain.repository

import com.ort.mediconsent.domain.model.Question

interface QuestionRepository {
    suspend fun getExamenQuestions(idFormulaire: Int): List<Question>
}