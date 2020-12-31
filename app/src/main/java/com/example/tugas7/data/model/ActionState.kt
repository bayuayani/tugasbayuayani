package com.example.tugas7.data.model

data class ActionState<T>(
    val data: T? = null,
    val message: String? = null,
    val isSuccsess: Boolean = true,
    var isConsumed: Boolean = false
)
