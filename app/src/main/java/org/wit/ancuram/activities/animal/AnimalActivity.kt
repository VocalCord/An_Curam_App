package org.wit.ancuram.activities.animal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_ancuram.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.ancuram.R
import org.wit.ancuram.helpers.readImage
import org.wit.ancuram.helpers.readImageFromPath
import org.wit.ancuram.helpers.showImagePicker
import org.wit.ancuram.main.MainApp
import org.wit.ancuram.models.animal.AnimalModel
import org.wit.ancuram.models.animal.Sighting

class AnimalActivity : AppCompatActivity(), AnkoLogger {

    var animal = AnimalModel()
    lateinit var app : MainApp
    var edit = false

    val IMAGE_REQUEST = 1

    val SIGHTING_REQUEST = 2
    var sighting = Sighting(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ancuram)
        info("An Cúram Main Activity started..")
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("animal_edit")) {
            edit = true
            animal = intent.extras?.getParcelable<AnimalModel>("animal_edit")!!
            animalName.setText(animal.commonName)
            animalNameIrish.setText(animal.irishName)
            btnAdd.setText(R.string.save_addAnimal)
            animalImage.setImageBitmap(readImageFromPath(this, animal.image))
            if (animal.image != null) {
                chooseImage.setText(R.string.change_animal_image)
            }
        }

        animalSighting.setOnClickListener {
            startActivityForResult(intentFor<MapsActivity>().putExtra("sighting", sighting), SIGHTING_REQUEST)
        }

        animalSighting.setOnClickListener {
            val sighting = Sighting(52.245696, -7.139102, 15f)
            if (animal.zoom != 0f) {
                sighting.lat =  animal.lat
                sighting.lng = animal.lng
                sighting.zoom = animal.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("sighting", sighting), SIGHTING_REQUEST)
        }

        btnAdd.setOnClickListener() {
            animal.commonName = animalName.text.toString()
            animal.irishName = animalNameIrish.text.toString()
            if (animal.commonName.isEmpty()) {
                toast(R.string.enter_animal_name)
            }else {
                if (edit) {
                    app.animals.update(animal.copy())
                } else {
                    app.animals.create(animal.copy())
                }
            }
            info("add Button Pressed: ${animal}")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_animal, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.animals.delete(animal)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    animal.image = data.getData().toString()
                    animalImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_animal_image)
                }
            }
            SIGHTING_REQUEST -> {
                if (data != null) {
                    val sighting = data.extras?.getParcelable<Sighting>("sighting")!!
                    animal.lat = sighting.lat
                    animal.lng = sighting.lng
                    animal.zoom = sighting.zoom
                }
            }
        }
    }


}