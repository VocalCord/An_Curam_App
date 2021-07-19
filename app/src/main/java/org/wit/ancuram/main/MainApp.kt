package org.wit.ancuram.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.ancuram.models.AnimalMemStore

class MainApp : Application(), AnkoLogger {

    val animals = AnimalMemStore()

    override fun onCreate() {
        super.onCreate()
        info("An Cúram app started")

        /*animals.add(AnimalModel("Red Fox", "Sionnach"))
        animals.add(AnimalModel("Common Barn owl", "Scréachóg Reilge"))
        animals.add(AnimalModel("Irish stoat", "Easóg"))
         */
    }
}