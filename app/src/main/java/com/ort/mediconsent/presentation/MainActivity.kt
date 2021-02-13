package com.ort.mediconsent.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.avis.AvisFragment
import com.ort.mediconsent.presentation.connect.ConnectFragment
import com.ort.mediconsent.presentation.question.QuestionFragment
import com.ort.mediconsent.presentation.rdvList.RdvListFragment
import com.ort.mediconsent.presentation.search.SearchFragment
import com.ort.mediconsent.presentation.signature.SignatureFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            add(R.id.fragment_container, ConnectFragment())
        }
    }

    fun displaySearchLayout() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, SearchFragment())
            //addToBackStack permet de conserver l'état précédant
            addToBackStack(null)
        }
    }

    fun displayRdvListLayout(id: Int) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, RdvListFragment.newInstance(id))
        }
    }

    fun displayQuestionsLayout(idFormulaire: Int, examen: Examen) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, QuestionFragment.newInstance(idFormulaire, examen))
            addToBackStack(null)
        }
    }

    fun displaySignatureLayout(idExamen: Int, reponses: List<Reponse>) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, SignatureFragment.newInstance(idExamen, reponses))
            addToBackStack(null)
        }
    }

    fun displayAvisLayout(examen: Examen) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, AvisFragment.newInstance(examen))
            addToBackStack(null)
        }
    }
}