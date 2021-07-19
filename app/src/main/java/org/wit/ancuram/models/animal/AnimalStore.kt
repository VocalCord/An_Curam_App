package org.wit.ancuram.models.animal

import org.wit.ancuram.models.animal.AnimalModel

interface AnimalStore {
        fun findAll(): List<AnimalModel>
        fun create(animal: AnimalModel)
        fun update(animal: AnimalModel)
    }