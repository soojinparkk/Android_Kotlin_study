package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_listview.*

class ListviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("$i Car ", "$i engine"))
        }

        val adapter = ListViewAdapter(carList, LayoutInflater.from(this))
        listView.adapter = adapter
        
        listView.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this, "$carName $carEngine", Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(
    val carForList: ArrayList<CarForList>,
    val layoutInflater: LayoutInflater
): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /*
        val view = layoutInflater.inflate(R.layout.item_view, null)
        val carNameView = view.findViewById<TextView>(R.id.car_name)
        val carEngineView = view.findViewById<TextView>(R.id.car_engine)

        carNameView.setText(carForList.get(position).name)
        carEngineView.setText(carForList.get(position).engine)

        return view
        */

        val view: View
        val holder: ViewHolder

        // 처음 보여지는 부분
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_view, null)
            holder = ViewHolder()
            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)

            view.tag = holder
        }
        // 나중에 보여지는 부분 -> 위에 view 떼어서 아래에서 재활용
        else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.carName?.setText(carForList.get(position).name)
        holder.carEngine?.setText(carForList.get(position).engine)

        return view
    }

    // position에 해당하는 리스트 하나
    override fun getItem(position: Int): Any {
        return carForList.get(position)
    }

    // 해당 position에 위치해 있는 리스트의 ID
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // 그리고자 하는 아이템 리스트의 전체 갯수
    override fun getCount(): Int {
        return carForList.size
    }
}

// findViewById -> 좀 무거운 함수라 가볍게 사용하기 힘듦
// findViewById 한번만 사용하고 대신 holder 사용
class ViewHolder {
    var carName: TextView? = null
    var carEngine: TextView? = null
}