package com.ort.mediconsent.domain.repository

import android.graphics.Bitmap
import com.ort.mediconsent.domain.model.Reponse

interface ReponseRepository {
    suspend fun sendReponses(reponses: List<Reponse>)
    suspend fun sendSignature(bitmap: Bitmap)
}