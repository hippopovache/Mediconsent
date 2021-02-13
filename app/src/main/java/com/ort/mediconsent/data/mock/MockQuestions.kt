package com.ort.mediconsent.data.mock

import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.repository.QuestionRepository

class MockQuestions : QuestionRepository {
    override suspend fun getExamenQuestions(idFormulaire: Int): List<Question> {
        return listOf(
            Question(
                1,
                "Avez-vous des soucis de santé?",
                true,
                "icone"
            ), Question(
                2,
                "Quel est votre problème?",
                false,
                "icone",
            ),
            Question(
                3,
                "Avez vous peur?",
                true,
                "icone"
            ),
            Question(
                4,
                "est ce qui pourrait vous faire peur?",
                false,
                "icone"
            )
        )
    }
}