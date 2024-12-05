package com.example.spiner

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

              private lateinit var toolbar: Toolbar
              private lateinit var spinner1: Spinner
              private lateinit var listTv: ListView
              private lateinit var button: Button
              private lateinit var spinner2: Spinner
              private lateinit var name: EditText
              private lateinit var surname: EditText
              private lateinit var age: EditText

              var person: MutableList<Person> = mutableListOf()
              var item1 = ""; var item2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            title = "Подбор персонала"
            toolbar.setTitleTextColor(Color.WHITE)
            spinner1 = findViewById(R.id.spinner1)
            listTv = findViewById(R.id.listTv)
            button = findViewById(R.id.button)
            spinner2 = findViewById(R.id.spinner2)
            name = findViewById(R.id.nameText)
            surname = findViewById(R.id.surnameText)
            age = findViewById(R.id.ageText)




           val spinerAdapter1 = ArrayAdapter.createFromResource(this,R.array.spiner1,android.R.layout.simple_spinner_item)
           val spinerAdapter2 = ArrayAdapter.createFromResource(this,R.array.spiner2,android.R.layout.simple_spinner_item)

             spinerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
             spinerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

              spinner1.adapter = spinerAdapter1
              spinner2.adapter = spinerAdapter2

        val personBase = Person.dataBase
                        personBase.forEach { person += it }
        val ListAdapter = MyAdapter(this,person)
                          listTv.adapter = ListAdapter



                     button.setOnClickListener {

                         if (name.text.isEmpty() or surname.text.isEmpty()) return@setOnClickListener

                         val pers = Person(name.text.toString(),surname.text.toString(),age.text.toString(),item2)
                            person.add(pers); personBase.add(pers); ListAdapter.notifyDataSetChanged()
                         name.text.clear();surname.text.clear();age.text.clear()

                     }


                  listTv.onItemClickListener = AdapterView.OnItemClickListener { s,v,posishion,id ->

                      if ( item1 != "Все" ) { Toast.makeText(this,"Удаление невозможно",Toast.LENGTH_LONG).show(); return@OnItemClickListener}

                      val builder = AlertDialog.Builder(this)
                          builder.setTitle("Удалить работника?")
                                 .setNegativeButton("Нет") { t,p -> t.cancel()}
                                 .setPositiveButton("Да") { t,p-> personBase.removeAt(posishion);person.removeAt(posishion); ListAdapter.notifyDataSetChanged()}.create().show()
                                                                      }

                   val itemSelected1: AdapterView.OnItemSelectedListener =
                       object: AdapterView.OnItemSelectedListener{

                           override fun onItemSelected(
                               parent: AdapterView<*>?,
                               view: View?,
                               position: Int,
                               id: Long
                           ) {

                               item1 = parent?.getItemAtPosition(position) as String

                               if (position == 0) { person.clear(); person.addAll(personBase)} else
                                                  { person.clear(); person.addAll(personBase.filter { it.role.take(4) == item1.take(4) })}
                               ListAdapter.notifyDataSetChanged()

                           }

                           override fun onNothingSelected(parent: AdapterView<*>?) {} }

                      spinner1.onItemSelectedListener = itemSelected1

        val itemSelected2: AdapterView.OnItemSelectedListener =
            object: AdapterView.OnItemSelectedListener{

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {  item2 = parent?.getItemAtPosition(position) as String }

                override fun onNothingSelected(parent: AdapterView<*>?) {} }

        spinner2.onItemSelectedListener = itemSelected2

        }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit,menu)
        return super.onCreateOptionsMenu(menu) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         val builder = AlertDialog.Builder(this)
            builder.setTitle("Выход из программы").setMessage("Действительно выйти?")
                   .setNegativeButton("Нет") {d,v -> d.cancel()}
                   .setPositiveButton("Да") {d,v -> finish()}.create().show()
        return super.onOptionsItemSelected(item)
    }




}

