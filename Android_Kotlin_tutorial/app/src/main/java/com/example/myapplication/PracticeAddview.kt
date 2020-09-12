package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class PracticeAddview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_addview)

        val phoneBook = createFakePhoneBook()
        createPhoneBookList(phoneBook)

    }

    fun createFakePhoneBook(fakeNum: Int = 10, phoneBook: PhoneBook= PhoneBook()): PhoneBook {
        for (i in 0 until fakeNum) {
            phoneBook.addPerson(
                Person(name = "$i Person", num = "$i Number")
            )
        }
        return phoneBook
    }

    fun createPhoneBookList(phoneBook: PhoneBook) {
        val layoutInflater = LayoutInflater.from(this)
        val container = findViewById<LinearLayout>(R.id.practice_container)
        for (i in 0 until phoneBook.personList.size) {
            val view = layoutInflater.inflate(R.layout.item_view_practice, null)
            val personNameView = view.findViewById<TextView>(R.id.phone_name)
            personNameView.setText(phoneBook.personList[i].name)
            addSetOnClickListener(phoneBook.personList[i], view)
            container.addView(view)
        }
    }

    fun addSetOnClickListener(person: Person, view: View) {
        view.setOnClickListener {
            val intent = Intent(this, PracticeAddviewDetails::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("num", person.num)
            startActivity(intent)
        }
    }
}

class PhoneBook() {
    val personList = ArrayList<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }
}

class Person(val name:String, var num: String) {

}
