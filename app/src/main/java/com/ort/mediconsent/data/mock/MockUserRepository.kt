package com.ort.mediconsent.data.mock

import com.ort.mediconsent.domain.repository.UserRepository

class MockUserRepository : UserRepository {
    //vu qu'on override, il faut utiliser le suspend comme dans la fonction de base
    override suspend fun getUserConnect(login: Int, mdp: String): Boolean {
        return login === 1 && mdp == "1"
    }
}