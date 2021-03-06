package org.wit.ancuram.activities.animal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_animal.view.*
import org.wit.ancuram.R
import org.wit.ancuram.helpers.readImageFromPath
import org.wit.ancuram.models.animal.AnimalModel

interface AnimalListener {
    fun onAnimalClick(animal: AnimalModel)
}

class AnimalAdapter constructor(private var animals: List<AnimalModel>,
                                private val listener: AnimalListener
) : RecyclerView.Adapter<AnimalAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_animal,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val animal = animals[holder.adapterPosition]
        holder.bind(animal, listener)
    }

    override fun getItemCount(): Int = animals.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(animal: AnimalModel, listener: AnimalListener) {
            itemView.animalName.text = animal.commonName
            itemView.animalNameIrish.text = animal.irishName
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, animal.image))
            itemView.setOnClickListener { listener.onAnimalClick(animal)
            }
        }
    }

}