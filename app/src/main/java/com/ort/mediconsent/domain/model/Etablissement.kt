package com.ort.mediconsent.domain.model

data class Etablissement(
    val id_etablissement: Int,
    val nom_etablissement: String,
    val adresse_etablissement: String,
    val telephone_etablissement: String,
    val sigle_etablissement: String,
)
