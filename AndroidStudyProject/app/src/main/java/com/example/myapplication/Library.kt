package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library.*

class Library : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        Glide.with(this@Library)
            .load("https://miro.medium.com/max/318/1*1OKmA2EdGln8O6RCVORgGg.png")
            .centerCrop()
            .into(glide)
    }
}
