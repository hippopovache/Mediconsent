package com.ort.mediconsent.domain.repository

import com.ort.mediconsent.domain.model.Examen

interface UserRepository {
    //suspend sous entend que le code utilise co routines (async)
    suspend fun getUserConnect(id: String, mdp: String): Boolean

    suspend fun getUserRdvForToday(firstname: String, name: String): Examen
}