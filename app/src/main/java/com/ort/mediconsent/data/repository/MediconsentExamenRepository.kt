package com.ort.mediconsent.data.repository


import com.google.gson.GsonBuilder
import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.data.model.ApiExamen
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.ExamenRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MediconsentExamenRepository : ExamenRepository {
    private val retrofit: Retrofit
    private val retrofitLocal: Retrofit

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

        retrofitLocal = Retrofit.Builder()
            .baseUrl(MediconsentApi.BASE_URL_LOCAL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val api = retrofit.create(MediconsentApi::class.java)
    private val apiLocal = retrofitLocal.create(MediconsentApi::class.java)


    override suspend fun getUserRdvForToday(firstname: String, name: String): Examen {
        return api.getUserRdvForToday(firstname, name)[0].toExamen()
    }

    override suspend fun getExamenById(id: Int): Examen {
        return api.getExamenById(id).toExamen()
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