package org.wit.ancuram.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_animal_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.ancuram.R
import org.wit.ancuram.main.MainApp
import org.wit.ancuram.models.AnimalModel


class AnimalListActivity : AppCompatActivity(), AnimalListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = AnimalAdapter(app.animals.findAll(), this)

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

    override fun onAnimalClick(animal: AnimalModel) {
        startActivityForResult(intentFor<AnCuramActivity>().putExtra("animal_edit", animal), 0)
    }

}