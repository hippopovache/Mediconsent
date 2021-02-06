package com.ort.mediconsent.domain.model

data class Utilisateur(
    val id_utilisateur: Int,
    val nom_utilisateur: String,
    val prenom_utilisateur: String,
    val mot_de_passe_utilisateur: String,
    val numero_securite_sociale: String,
)
