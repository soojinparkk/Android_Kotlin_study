package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_pager.*
import kotlinx.android.synthetic.main.activity_tab_pager2.*

class TabPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager2)

        tab_layout_2.addTab(tab_layout_2.newTab().setText("ONE"))
        tab_layout_2.addTab(tab_layout_2.newTab().setText("TWO"))
        tab_layout_2.addTab(tab_layout_2.newTab().setText("THREE"))

        val adapter = PageAdapter(LayoutInflater.from(this))
        view_pager_2.adapter = adapter
        view_pager_2.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout_2))

        tab_layout_2.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager_2.currentItem = tab!!.position
            }
        })

    }
}

class PageAdapter (
    val inflater: LayoutInflater
): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when(position) {
            0 -> {
                val view = inflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = inflater.inflate(R.layout.fragment2, container, false)
                container.addView(view)
                return view
            }
            2 -> {
                val view = inflater.inflate(R.layout.fragment3, container, false)
                container.addView(view)
                return view
            }
            else -> {
                val view = inflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}