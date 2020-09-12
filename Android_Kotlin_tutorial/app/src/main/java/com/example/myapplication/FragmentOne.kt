package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {
/*
    // fragment -> activity data 전달할 때
    interface OnDataPassListener {
        fun onDataPass(s: String)
    }
    lateinit var dataPassListener: OnDataPassListener


    override fun onAttach(context: Context) {
        Log.d("Life_cycle", "F onAttach")
        super.onAttach(context)

        dataPassListener = context as OnDataPassListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Life_cycle", "F onCreate")
        super.onCreate(savedInstanceState)
    }

    // fragment가 interface를 처음 그릴 때 호출 (정적)
    override fun onCreateView(
        inflater: LayoutInflater,   // view를 그려주는 역할
        container: ViewGroup?,      // 부모 view
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Life_cycle", "F onCreateView")

        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    // fragment -> activity data 전달할 때
    // activity의 onCreate()에서 했던 작업을 여기에서 함
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Life_cycle", "F onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        passBtn.setOnClickListener {
            dataPassListener.onDataPass("hi")
        }

    }

    // activity -> fragment data 전달 받을 때
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("Life_cycle", "F onActivityCreated")

        val data = arguments?.getString("hi")
        Log.d("Data", data)

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d("Life_cycle", "F onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("Life_cycle", "F onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Life_cycle", "F onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("Life_cycle", "F onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("Life_cycle", "F onDestroyView")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.d("Life_cycle", "F onDetach")
        super.onDetach()
    }

 */
}