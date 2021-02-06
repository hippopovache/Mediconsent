package com.ort.mediconsent.data.repository


import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.data.model.ApiExamen
import com.ort.mediconsent.data.model.ApiUtilisateur
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Utilisateur
import com.ort.mediconsent.domain.repository.ExamenRepository
import com.ort.mediconsent.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MediconsentExamenRepository : ExamenRepository {
    private val retrofit: Retrofit
    private val retrofitLocal: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(MediconsentApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitLocal = Retrofit.Builder()
            .baseUrl(MediconsentApi.BASE_URL_LOCAL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api = retrofit.create(MediconsentApi::class.java)
    private val apiLocal = retrofitLocal.create(MediconsentApi::class.java)


    override suspend fun getUserRdvForToday(firstname: String, name: String): Examen {
            return apiLocal.getUserRdvForToday(firstname, name)[0].toExamen()
    }

    private fun ApiExamen.toExamen() = Examen(
        this.id_examen,
        this.type_examen,
        this.etablissement,
        this.avis,
        this.date_examen,
        this.consentement,
        this.doc_consentement,
        this.signature,
        this.annuler,
        this.date_annulation
    )
}