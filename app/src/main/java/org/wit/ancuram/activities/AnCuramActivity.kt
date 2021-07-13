package org.wit.ancuram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ancuram.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.ancuram.R

class AnCuramActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ancuram)
        info("An Cúram Main Activity started..")

        btnAdd.setOnClickListener() {
            val animalName = animalName.text.toString()
            if (animalName.isNotEmpty()) {
                info("add Animal Button Pressed: $animalName")
            }
            else {
                toast ("Please Enter the Animals Name")
            }
        }
    }


}