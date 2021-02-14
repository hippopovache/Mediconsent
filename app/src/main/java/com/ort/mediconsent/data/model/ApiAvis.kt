package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName

data class ApiAvis(
    @SerializedName("id_avis")
    val id_avis: Int,
    @SerializedName("notes")
    val notes: Int,
    @SerializedName("commentaire")
    val commentaire: String?,
)

