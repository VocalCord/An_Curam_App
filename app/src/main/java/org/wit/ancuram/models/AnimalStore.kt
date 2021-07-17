package org.wit.ancuram.models

interface AnimalStore {
        fun findAll(): List<AnimalModel>
        fun create(animal: AnimalModel)
        fun update(animal: AnimalModel)
    }