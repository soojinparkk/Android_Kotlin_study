package com.example.mygallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

// ViewPager2
// FragmentStatePagerAdapter -> FragmentStateAdapter 사용
class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentStateAdapter(fragmentManager) {

    // 뷰페이저가 표시할 프래그먼트 목록
    private val items = ArrayList<Fragment>()

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return items.size
    }

    // position 위치의 프래그먼트 반환
    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    // 아이템 갱신 (외부에서 아이템 추가 가능)
    fun updateFragments(items: List<Fragment>) {
        this.items.addAll(items)
    }
}

/*
class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val items = ArrayList<Fragment>()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    fun updateFragments(items: List<Fragment>) {
        this.items.addAll(items)
    }
}
*/
