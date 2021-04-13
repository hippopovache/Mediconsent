package com.ort.mediconsent.data.api

import com.ort.mediconsent.BuildConfig
import com.ort.mediconsent.data.model.ApiExamen
import com.ort.mediconsent.data.model.ApiQuestion
import com.ort.mediconsent.data.model.ApiUtilisateur
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MediconsentApi {
    companion object {
        const val BASE_URL = BuildConfig.API_URL
    }

    @GET("checkLogin2/{nom}/{mdp}")
    suspend fun getUserConnect(
        @Path("nom") nom: String,
        @Path("mdp") mdp: String,
    ): ApiUtilisateur

    @GET("examen/{prenom}/{nom}")
    suspend fun getUserRdvForToday(
        @Path("prenom") firstName: String,
        @Path("nom") name: String,
    ): List<ApiExamen>

    @GET("examen/{id}")
    suspend fun getExamenById(
        @Path("id") id: Int,
    ): ApiExamen

    @GET("questions/formulaire/{id}")
    suspend fun getExamenQuestions(
        @Path("id") id: Int,
    ): List<ApiQuestion>

    @PUT("examen/save/")
    suspend fun updateExamen(
        @Body examen: Examen,
    )

    @PUT("reponse/save/")
    suspend fun insertReponse(
        @Body reponse: Reponse,
    )
}