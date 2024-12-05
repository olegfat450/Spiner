package com.example.spiner

   class  Person(val name: String,val surname: String,val age: String,val role: String) {

       companion object{
       val dataBase: MutableList<Person> = mutableListOf(
           Person("Олег", "Федотов", "49", "Водитель"),
           Person("Сергей", "Сергеев", "35", "Охранник"),
           Person("Елена","Катц","50","Диспетчер"),
           Person("Дмитрий","Казанский","25","Механик")
       )}

   }