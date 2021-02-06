package com.ort.mediconsent.domain.model

import java.util.*

data class Examen(
    val id_examen: Int,
    val typeExamen: TypeExamen,
    val etablissement: Etablissement?,
    val avis: Avis?,
    val date_examen: Date,
    val consentement: Boolean,
    val doc_consentement: String?,
    val signature: String?,
    val annuler: Boolean?,
    val date_annulation: Date?,
)
