package org.wit.ancuram.models.animal

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.ancuram.helpers.*
import java.util.*

val JSON_FILE = "animals.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AnimalModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AnimalJSONStore : AnimalStore, AnkoLogger {

    val context: Context
    var animals = mutableListOf<AnimalModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AnimalModel> {
        return animals
    }

    override fun create(animal: AnimalModel) {
        animal.id = generateRandomId()
        animals.add(animal)
        serialize()
    }


    override fun update(animal: AnimalModel) {
        val animalsList = findAll() as ArrayList<AnimalModel>
        var foundAnimal: AnimalModel? = animalsList.find { p -> p.id == animal.id }
        if (foundAnimal != null) {
            foundAnimal.commonName = animal.commonName
            foundAnimal.irishName = animal.irishName
            foundAnimal.image = animal.image

        }
        serialize()
    }

    override fun delete(animal: AnimalModel) {
        animals.remove(animal)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(animals, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        animals = Gson().fromJson(jsonString, listType)
    }
}