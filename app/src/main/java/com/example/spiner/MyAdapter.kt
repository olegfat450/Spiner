package com.example.spiner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(context: Context,person: MutableList <Person>): ArrayAdapter<Person>(context,R.layout.item,person) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //var view = convertView;

        val person = getItem(position)
       // if ( view == null ) {view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)}

       val view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)

        val name = view?.findViewById<TextView>(R.id.text_item1)
        val surname = view?.findViewById<TextView>(R.id.text_item2)
        val age = view?.findViewById<TextView>(R.id.texti_item4)
        val role = view?.findViewById<TextView>(R.id.text_item3)

        name?.text = person?.name?.capitalize()
        surname?.text = person?.surname?.capitalize()
        age?.text = "Возраст: ${person?.age} лет"
        role?.text = person?.role

        return view!!

    }
}