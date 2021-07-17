package org.wit.ancuram.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}


class AnimalMemStore : AnimalStore, AnkoLogger {

    val animals = ArrayList<AnimalModel>()

    override fun findAll(): List<AnimalModel> {
        return animals
    }

    override fun create(animal: AnimalModel) {
        animal.id = getId()
        animals.add(animal)
        logAll()
    }

    override fun update(animal: AnimalModel) {
        var foundAnimal: AnimalModel? = animals.find { p -> p.id == animal.id }
        if (foundAnimal != null) {
            foundAnimal.commonName = animal.commonName
            foundAnimal.irishName = animal.irishName
            logAll()
        }
    }

    fun logAll() {
        animals.forEach{ info("${it}") }
    }


}