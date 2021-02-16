package com.ort.mediconsent.presentation

import com.ort.mediconsent.presentation.language.LocaleHelper
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ort.mediconsent.GlobalVariables
import com.ort.mediconsent.R
import com.ort.mediconsent.domain.model.Examen
import com.ort.mediconsent.domain.model.Question
import com.ort.mediconsent.domain.model.Reponse
import com.ort.mediconsent.presentation.avis.AvisFragment
import com.ort.mediconsent.presentation.connect.ConnectFragment
import com.ort.mediconsent.presentation.pdf.PdfFragment
import com.ort.mediconsent.presentation.question.QuestionFragment
import com.ort.mediconsent.presentation.rdvList.RdvListFragment
import com.ort.mediconsent.presentation.search.SearchFragment
import com.ort.mediconsent.presentation.signature.SignatureFragment


class MainActivity : AppCompatActivity() {

    private lateinit var m: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (GlobalVariables.isConnected) {
            displaySearchLayout()
        } else {
            supportFragmentManager.commit {
                add(R.id.fragment_container, ConnectFragment())
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        if (menu != null) {
            m = menu
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // switch kotlin
        when (item.itemId) {
            R.id.lang_en_btn -> {
                LocaleHelper.setLocale(this, "en")
            }
            R.id.lang_fr_btn -> {
                LocaleHelper.setLocale(this, "fr")
            }
        }
        finish()
        startActivity(intent)
        return true
    }


    fun displaySearchLayout() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, SearchFragment())
            //addToBackStack permet de conserver l'état précédant
            addToBackStack(null)
        }
    }

    fun displayRdvListLayout(id: Int, prenom: String, nom: String) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, RdvListFragment.newInstance(id, prenom, nom))
        }
    }

    fun displayQuestionsLayout(idFormulaire: Int, examen: Examen, prenom: String, nom: String) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container,
                QuestionFragment.newInstance(idFormulaire, examen, prenom, nom)
            )
            addToBackStack(null)
        }
    }

    fun displaySignatureLayout(
        examen: Examen,
        reponses: List<Reponse>,
        questions: List<Question>,
        prenom: String,
        nom: String,
    ) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container,
                SignatureFragment.newInstance(examen, reponses, questions, prenom, nom)
            )
            addToBackStack(null)
        }
    }

    fun displayAvisLayout(examen: Examen) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, AvisFragment.newInstance(examen))
            addToBackStack(null)
        }
    }

    fun displayPdfLayout(
        examen: Examen,
        questions: List<Question>,
        reponses: List<Reponse>,
        signature: Bitmap, prenom: String, nom: String,
    ) {
        supportFragmentManager.commit {
            replace(
                R.id.fragment_container,
                PdfFragment.newInstance(examen, questions, reponses, signature, prenom, nom)
            )
        }
    }
}