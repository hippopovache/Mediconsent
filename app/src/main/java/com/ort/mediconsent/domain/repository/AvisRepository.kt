package com.ort.mediconsent.domain.repository

import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Examen

interface AvisRepository {
    suspend fun sendAvis(examen: Examen, avis: Avis)
}