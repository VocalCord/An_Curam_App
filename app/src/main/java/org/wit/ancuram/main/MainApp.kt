package org.wit.ancuram.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.ancuram.models.animal.AnimalJSONStore
import org.wit.ancuram.models.animal.AnimalMemStore
import org.wit.ancuram.models.animal.AnimalStore

class MainApp : Application(), AnkoLogger {

    lateinit var animals : AnimalStore

    override fun onCreate() {
        super.onCreate()
        animals = AnimalJSONStore(applicationContext)
        info("An Cúram app started")

        /*animals.add(AnimalModel("Red Fox", "Sionnach"))
        animals.add(AnimalModel("Common Barn owl", "Scréachóg Reilge"))
        animals.add(AnimalModel("Irish stoat", "Easóg"))
         */
    }
}