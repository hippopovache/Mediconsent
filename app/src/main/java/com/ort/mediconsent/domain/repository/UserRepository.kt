package com.ort.mediconsent.domain.repository

interface UserRepository {
    //suspend sous entend que le code utilise co routines (async)
    suspend fun getUserConnect(id: Int, mdp: String): Boolean
}