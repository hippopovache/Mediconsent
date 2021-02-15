package com.ort.mediconsent.domain.model

import java.util.*

data class Examen(
    val id_examen: Int,
    val type_examen: TypeExamen,
    val etablissement: Etablissement?,
    var avis: Avis?,
    val date_examen: Date,
    var consentement: Boolean,
    var doc_consentement: String?,
    var signature: String?,
    val annuler: Boolean?,
    val date_annulation: Date?,
)
