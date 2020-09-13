package com.example.todolist

import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

class TodoListAdapter (realmResult: OrderedRealmCollection<Todo>)
    : RealmBaseAdapter<Todo>(realmResult) {

    // 아이템에 표시하는 뷰 구성
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh: TodoViewHolder
        val view: View

        // convertView는 아이템이 작성되기 전에는 null
        // 한 번 작성되면 이전에 작성했던 뷰 전달
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_todo, parent, false)

            vh = TodoViewHolder(view)   // 뷰 홀더 객체 초기화
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as TodoViewHolder
        }

        if (adapterData != null) {  // 값이 있다면 해당 위치의 data 전달
            val item = adapterData!![position]
            vh.contentTextView.text = item.title
            vh.dateTextView.text = DateFormat.format("yyyy/MM/dd", item.date)
        }

        return view
    }

    override fun getItemId(position: Int): Long {
        if (adapterData != null)
            return adapterData!![position].id
        return super.getItemId(position)
    }
}

class TodoViewHolder (view: View) {
    val dateTextView: TextView = view.findViewById(R.id.dateItemView)
    val contentTextView: TextView = view.findViewById(R.id.contentItemView)
}