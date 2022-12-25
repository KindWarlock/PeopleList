package ru.myitschool.peoplelistk

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var people = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>

    private fun genPeople(peopleNumber: Int) {
        val firstNames = resources.getStringArray(R.array.first_name)
        val lastNames = resources.getStringArray(R.array.last_name)

        for (i in 0 until peopleNumber) {
            val first = firstNames[Random.nextInt(firstNames.size)]
            val last = lastNames[Random.nextInt(lastNames.size)]
            people.add("$first $last")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvPeople = findViewById<ListView>(R.id.people)

        genPeople(6)
        adapter = ArrayAdapter<String>(this, R.layout.item, people)
        lvPeople.adapter = adapter // задаём адаптер (посредник) для отображения данных на списке

    }

    fun onAddPersonClick(view: View) {
        val txtField = findViewById<EditText>(R.id.new_name)
        val newName = txtField.text.toString()
        if (newName == "") {
            return
        }
        adapter.add(newName)
        adapter.notifyDataSetChanged()
        txtField.setText("")
    }
}