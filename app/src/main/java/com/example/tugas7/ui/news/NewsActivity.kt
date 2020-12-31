package com.example.tugas7.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas7.R

class NewsActivity : AppCompatActivity() {
    object OPEN_NEWS {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}