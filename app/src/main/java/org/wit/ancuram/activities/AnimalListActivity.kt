package org.wit.ancuram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.ancuram.R
import org.wit.ancuram.main.MainApp

class AnimalListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)
        app = application as MainApp
    }
}