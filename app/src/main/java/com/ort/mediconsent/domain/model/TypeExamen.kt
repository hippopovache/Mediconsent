package com.ort.mediconsent.domain.model

data class TypeExamen(
    val id_type_examen: Int,
    val libelle_type_examen: String,
    val formulaire: Formulaire
)
