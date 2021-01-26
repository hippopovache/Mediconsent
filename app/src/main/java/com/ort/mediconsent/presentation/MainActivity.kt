package com.ort.mediconsent.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ort.mediconsent.R
import com.ort.mediconsent.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect)
    }

    fun displaySearchLayout() {
        supportFragmentManager.commit {
            add(R.id.fragment_container, SearchFragment())
        }
    }
}