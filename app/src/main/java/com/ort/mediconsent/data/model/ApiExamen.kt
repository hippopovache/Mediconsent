package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName
import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Etablissement
import com.ort.mediconsent.domain.model.TypeExamen
import java.util.*

data class ApiExamen(
    @SerializedName("id_examen")
    val id_examen: Int,
    @SerializedName("type_examen")
    val type_examen: TypeExamen,
    @SerializedName("etablissement")
    val etablissement: Etablissement?,
    @SerializedName("avis")
    val avis: Avis?,
    @SerializedName("date_examen")
    val date_examen: Date,
    @SerializedName("consentement")
    val consentement: Boolean,
    @SerializedName("doc_consentement")
    val doc_consentement: String?,
    @SerializedName("signature")
    val signature: String?,
    @SerializedName("annuler")
    val annuler: Boolean,
    @SerializedName("date_annulation")
    val date_annulation: Date?,

    )
