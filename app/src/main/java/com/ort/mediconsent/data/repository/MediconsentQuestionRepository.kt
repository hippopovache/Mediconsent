package com.ort.mediconsent.data.repository


import com.ort.mediconsent.data.api.MediconsentApi
import com.ort.mediconsent.data.model.ApiQuestion
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.repository.QuestionRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MediconsentQuestionRepository : QuestionRepository {
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


    override suspend fun getExamenQuestions(idFormulaire: Int): List<Question> {
        return api.getExamenQuestions(idFormulaire).map {
            it.toQuestion()
        }
    }

    private fun ApiQuestion.toQuestion() = Question(
        this.id_question,
        this.libelle_question,
        this.isCheckbox,
        this.icone
    )


}