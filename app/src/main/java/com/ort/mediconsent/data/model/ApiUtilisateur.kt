package com.ort.mediconsent.data.model

import com.google.gson.annotations.SerializedName

data class ApiUtilisateur(
    @SerializedName("id_utilisateur")
    val id_utilisateur: Int,
    @SerializedName("nom_utilisateur")
    val nom_utilisateur: String,
    @SerializedName("prenom_utilisateur")
    val prenom_utilisateur: String,
    @SerializedName("mot_de_passe_utilisateur")
    val mot_de_passe_utilisateur: String,
    @SerializedName("numero_securite_sociale")
    val numero_securite_sociale: String,
)
