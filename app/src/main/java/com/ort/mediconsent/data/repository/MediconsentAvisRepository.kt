package com.ort.mediconsent.data.repository


import com.google.gson.GsonBuilder
import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.AvisRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MediconsentAvisRepository : AvisRepository {
    private val retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(MediconsentApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            }

    private val api = retrofit.create(MediconsentApi::class.java)
    override suspend fun sendAvis(examen: Examen, avis: Avis) {
        examen.avis = avis
        api.updateExamen(examen)
    }
}