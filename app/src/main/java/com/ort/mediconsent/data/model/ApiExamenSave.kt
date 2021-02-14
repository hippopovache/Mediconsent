package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiExamenSave(
    @SerializedName("id_examen")
    val id_examen: Int,
    @SerializedName("id_type_examen")
    val id_type_examen: Int,
    @SerializedName("id_etablissement")
    val id_etablissement: Int,
    @SerializedName("id_avis")
    val id_avis: Int,
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
