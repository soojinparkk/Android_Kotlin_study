package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,   // view를 그려주는 역할
        container: ViewGroup?,      // 부모 view
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Life_cycle", "F onCreateView")

        return inflater.inflate(R.layout.fragment2, container, false)
    }
}