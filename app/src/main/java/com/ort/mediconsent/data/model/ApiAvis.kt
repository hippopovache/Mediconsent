package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName

data class ApiAvis(
    @SerializedName("id_avis")
    val id_avis: Int,
    @SerializedName("note")
    val note: Double,
    @SerializedName("commentaire")
    val commentaire: String?,
)
