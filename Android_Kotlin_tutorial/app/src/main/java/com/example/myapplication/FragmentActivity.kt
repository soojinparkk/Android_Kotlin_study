package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity()
    //, FragmentOne.OnDataPassListener
{
/*
    override fun onDataPass(s: String) {
        Log.d("pass", ""+s)
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("Life_cycle", "onCreate")

        // fragment를 동적으로 작동하는 방법
        val fragmentOne : FragmentOne = FragmentOne()

        // ++ fragment에 data를 넣어주는 방법
        val bundle : Bundle = Bundle()
        bundle.putString("hi", "hihi")
        fragmentOne.arguments = bundle

        // fragment를 replace / add 하는 방법
        btn.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager

            // Transaction
            // 작업의 단위 -> 시작과 끝이 있음
            val fragmentTransaction = fragmentManager.beginTransaction()    // 시작
            fragmentTransaction.replace(R.id.container, fragmentOne)    // 할 일
            fragmentTransaction.commit()    // 끝

            // 끝을 내는 방법
            // 1. commit()      -> 시간 될 때 해 (안정적)
            // 2. commitnow()   -> 지금 당장 해
        }

        // fragment를 remove / detach 하는 방법
        btn2.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(fragmentOne)
            fragmentTransaction.commit()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("Life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life_cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life_cycle", "onDestroy")
    }
}
