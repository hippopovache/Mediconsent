package com.ort.mediconsent.data.repository


import android.graphics.Bitmap
import android.util.Base64
import com.google.gson.GsonBuilder
import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.domain.repository.ReponseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class MediconsentReponseRepository : ReponseRepository {
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
    override suspend fun sendReponses(reponses: List<Reponse>) {
        for (reponse in reponses) {
            apiLocal.insertReponse(reponse)
        }
    }

    override suspend fun sendSignature(examen: Examen, bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        val encodedImageString = Base64.encodeToString(b, Base64.DEFAULT)
        examen.signature = encodedImageString
        examen.consentement = true
        apiLocal.updateExamen(examen)
    }


}