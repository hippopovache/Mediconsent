package com.ort.mediconsent.domain.repository

import com.ort.mediconsent.domain.model.Examen

interface ExamenRepository {
    suspend fun getUserRdvForToday(firstname: String, name: String): Examen
}