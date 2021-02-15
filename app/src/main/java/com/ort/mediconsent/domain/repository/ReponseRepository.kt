package com.ort.mediconsent.domain.repository

import android.graphics.Bitmap
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import java.io.File

interface ReponseRepository {
    suspend fun sendReponses(reponses: List<Reponse>)
    suspend fun sendSignaturePdf(examen: Examen, file: File, bitmap: Bitmap)
}