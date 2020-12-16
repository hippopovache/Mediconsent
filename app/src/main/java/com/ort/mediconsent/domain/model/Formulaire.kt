package com.ort.mediconsent.domain.model

import java.util.*

data class Formulaire(
    val id_formulaire: Int,
    val libelle_formulaire: String,
    val langage: Langage,
    val version: Double,
    val date: Date,
    val typeExamen: Type_examen,
)
