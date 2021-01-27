package com.ort.mediconsent.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ort.mediconsent.R
import com.ort.mediconsent.presentation.connect.ConnectFragment
import com.ort.mediconsent.presentation.search.SearchFragment

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
}