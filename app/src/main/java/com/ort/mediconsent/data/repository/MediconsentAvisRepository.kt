package com.ort.mediconsent.data.repository


import com.google.gson.GsonBuilder
import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.data.model.ApiAvis
import com.ort.mediconsent.data.model.ApiExamenSave
import com.ort.mediconsent.domain.model.Avis
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.repository.AvisRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MediconsentAvisRepository : AvisRepository {
    private val retrofit: Retrofit
    private val retrofitLocal: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
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
    override suspend fun sendAvis(examen: Examen, avis: Avis) {
        examen.avis = avis
        apiLocal.updateExamen(examen)
    }

    private fun ApiAvis.toAvis() = Avis(
        this.id_avis,
        this.notes,
        this.commentaire
    )

    private fun Examen.toApiExamenSave() = ApiExamenSave(
        this.id_examen,
        this.type_examen.id_type_examen,
        this.etablissement!!.id_etablissement,
        this.avis!!.id_avis,
        this.date_examen,
        this.consentement,
        this.doc_consentement,
        this.signature,
        this.annuler!!,
        this.date_annulation
    )

}