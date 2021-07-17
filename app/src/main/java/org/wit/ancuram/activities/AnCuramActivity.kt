package org.wit.ancuram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("animal_edit")) {
            animal = intent.extras?.getParcelable<AnimalModel>("animal_edit")!!
            animalName.setText(animal.commonName)
            animalNameIrish.setText(animal.irishName)
        }

        btnAdd.setOnClickListener() {
            animal.commonName = animalName.text.toString()
            animal.irishName = animalNameIrish.text.toString()
            if (animal.commonName.isNotEmpty()) {
                app.animals.create(animal.copy())
                info("add Button Pressed: ${animal}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter an animals name")
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_animal, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}