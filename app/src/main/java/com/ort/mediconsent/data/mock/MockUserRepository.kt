package com.ort.mediconsent.data.mock

import com.ort.mediconsent.domain.model.Etablissement
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.TypeExamen
import com.ort.mediconsent.domain.repository.UserRepository
import java.util.*

class MockUserRepository : UserRepository {
    //vu qu'on override, il faut utiliser le suspend comme dans la fonction de base
    override suspend fun getUserConnect(login: String, mdp: String): Boolean {
        return login == "1" && mdp == "1"
    }

    override suspend fun getUserRdvForToday(firstName: String, name: String): Examen {
        return Examen(
            1,
            TypeExamen(1, "type1"),
            Etablissement(
                1,
                "hopital de Die",
                "Adresse de l'hopital de Die",
                "0426325262",
                "url du sigle"
            ),
            null,
            Calendar.getInstance().time,
            false,
            null,
            null,
            null,
            null
        )
    }
}