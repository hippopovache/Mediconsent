package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName

data class ApiQuestion(
    @SerializedName("id_examen")
    val id_examen: Int,
    @SerializedName("id_question")
    val id_question: Int,
    @SerializedName("libelle_question")
    val libelle_question: String,
    @SerializedName("checkbox")
    val isCheckbox: Boolean,
    @SerializedName("icone")
    val icone: String?,
)
