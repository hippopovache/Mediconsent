package com.ort.mediconsent.domain.model

data class Reponse(
    val examen: Examen,
    val question: Question,
    val reponse: String,
)
