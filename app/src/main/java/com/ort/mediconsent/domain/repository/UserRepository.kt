package com.ort.mediconsent.domain.repository

import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Utilisateur

interface UserRepository {
    suspend fun getUserConnect(nom_utilisateur: String, mdp: String): Utilisateur
}