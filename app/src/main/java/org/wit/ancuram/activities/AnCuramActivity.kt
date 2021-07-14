package org.wit.ancuram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ancuram.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.ancuram.R
import org.wit.ancuram.main.MainApp
import org.wit.ancuram.models.AnimalModel

class AnCuramActivity : AppCompatActivity(), AnkoLogger {

    var animal = AnimalModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ancuram)
        info("An Cúram Main Activity started..")
        app = application as MainApp


        btnAdd.setOnClickListener() {
            animal.commonName = animalName.text.toString()
            animal.irishName = animalNameIrish.text.toString()
            if (animal.commonName.isNotEmpty()) {
                app!!.animals.add(animal.copy())
                info("add Button Pressed: $animalName")
                app!!.animals.forEach { info("add Animal Button Pressed: ${it.commonName}, ${it.irishName}")}
            }
            else {
                toast ("Please Enter the Animals Name")
            }
        }
    }


}