package org.wit.ancuram.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.ancuram.models.AnimalModel

class MainApp : Application(), AnkoLogger {

    val animals = ArrayList<AnimalModel>()

    override fun onCreate() {
        super.onCreate()
        info("An Cúram app started")
    }
}