package com.example.tugas7.data.model

data class NewsList(
    val data: List<News> = arrayListOf(),
    val length: Int = 0,
    val status: Int = 0
)
