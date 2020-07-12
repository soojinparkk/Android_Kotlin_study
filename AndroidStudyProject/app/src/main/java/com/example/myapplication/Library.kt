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
            .load("http://movie.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg")
            .centerCrop()
            .into(glide)
    }
}
