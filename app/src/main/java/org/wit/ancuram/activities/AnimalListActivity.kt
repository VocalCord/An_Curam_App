package org.wit.ancuram.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_animal_list.*
import kotlinx.android.synthetic.main.card_animal.view.*
import org.jetbrains.anko.startActivityForResult
import org.wit.ancuram.R
import org.wit.ancuram.main.MainApp
import org.wit.ancuram.models.AnimalModel

class AnimalListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = AnimalAdapter(app.animals)

        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<AnCuramActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }
}

class AnimalAdapter constructor(private var animals: List<AnimalModel>) :
    RecyclerView.Adapter<AnimalAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_animal,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val animal = animals[holder.adapterPosition]
        holder.bind(animal)
    }

    override fun getItemCount(): Int = animals.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(animal: AnimalModel) {
            itemView.animalName.text = animal.commonName
            itemView.animalNameIrish.text = animal.irishName
        }
    }


}