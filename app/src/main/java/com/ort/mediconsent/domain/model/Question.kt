package com.ort.mediconsent.domain.model

data class Question(
    val id_question: Int,
    val libelle_question: String,
    val isCheckbox: Boolean,
    val icone: String?,
)
