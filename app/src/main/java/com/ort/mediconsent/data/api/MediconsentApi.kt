package com.ort.mediconsent.data.api

import com.ort.mediconsent.data.model.ApiExamen
import com.ort.mediconsent.data.model.ApiQuestion
import com.ort.mediconsent.data.model.ApiUtilisateur
import retrofit2.http.GET
import retrofit2.http.Path

interface MediconsentApi {
    companion object {
        const val BASE_URL = "http://194.183.220.233:9095/Mediconsent/rest/"
        const val BASE_URL_LOCAL = "http://192.168.1.11:8080/rest/"
    }

    @GET("checkLogin2/{nom}/{mdp}")
    suspend fun getUserConnect(
        @Path("nom") nom: String,
        @Path("mdp") mdp: String
    ): ApiUtilisateur

    @GET("examen/{prenom}/{nom}")
    suspend fun getUserRdvForToday(
        @Path("prenom") firstName: String,
        @Path("nom") name: String,
    ): List<ApiExamen>

    @GET("examen/{id}")
    suspend fun getExamenById(
        @Path("id") id: Int
    ): ApiExamen

    @GET("questions/formulaire/{id}")
    suspend fun getExamenQuestions(
        @Path("id") id: Int
    ): List<ApiQuestion>
}