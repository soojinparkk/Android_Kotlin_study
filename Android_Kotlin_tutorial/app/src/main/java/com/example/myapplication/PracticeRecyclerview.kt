package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_practice_recyclerview.*

class PracticeRecyclerview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_recyclerview)

        val phoneBook = createFakePhoneBook()
        val recyclerAdapter = PhoneBookAdapter(phoneBook, LayoutInflater.from(this), activity = this)

        practice_recyclerview.adapter = recyclerAdapter
        practice_recyclerview.layoutManager = LinearLayoutManager(this)
    }

    fun createFakePhoneBook(fakeNum: Int = 10, phoneBook: PhoneBook= PhoneBook()): PhoneBook {
        for (i in 0 until fakeNum) {
            phoneBook.addPerson(
                Person(name = "$i Person", num = "$i Number")
            )
        }
        return phoneBook
    }
}

class PhoneBookAdapter (
    val phoneBookList: PhoneBook,
    val inflater: LayoutInflater,
    val activity: Activity
): RecyclerView.Adapter<PhoneBookAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val phoneName: TextView
        init {
            phoneName = itemView.findViewById(R.id.phone_name)

            itemView.setOnClickListener {
                val intent = Intent(activity, PracticeAddviewDetails::class.java)
                intent.putExtra("name", phoneBookList.personList.get(adapterPosition).name)
                intent.putExtra("num", phoneBookList.personList.get(adapterPosition).num)

                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view_practice, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phoneBookList.personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.phoneName.setText(phoneBookList.personList.get(position).name)
    }
}