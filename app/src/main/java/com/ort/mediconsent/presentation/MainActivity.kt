package com.ort.mediconsent.presentation

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.ort.mediconsent.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect)
    }

    fun displaySearchLayout(){
        supportFragmentManager.commit{
            replace(R.id.fragment_container, )
        }
    }
}