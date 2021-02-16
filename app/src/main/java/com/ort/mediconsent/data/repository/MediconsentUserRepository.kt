package com.ort.mediconsent.data.repository


import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.data.model.ApiUtilisateur
import com.ort.mediconsent.domain.model.Utilisateur
import com.ort.mediconsent.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MediconsentUserRepository : UserRepository {
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


    override suspend fun getUserConnect(nom_utilisateur: String, mdp: String): Utilisateur {
        return api.getUserConnect(nom_utilisateur, mdp).toUtilisateur()
    }

    private fun ApiUtilisateur.toUtilisateur() = Utilisateur(
        this.id_utilisateur,
        this.nom_utilisateur,
        this.prenom_utilisateur,
        this.mot_de_passe_utilisateur,
        this.numero_securite_sociale
    )

}