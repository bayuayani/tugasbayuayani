package com.example.tugas7.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.tugas7.data.model.ActionState
import com.example.tugas7.data.model.AuthUser
import com.example.tugas7.util.getObject
import com.example.tugas7.util.putObject

class AuthPref(val context:Context) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(AuthPref::class.java.name, Context.MODE_PRIVATE)
    }

    private companion object{
        const val AUTH_USER = "auth_user"
        const val IS_LOGIN = "is_login"
    }

    var authUser: AuthUser?
    get() = sp.getObject(AUTH_USER)
    private set(value) = sp.edit().putObject(AUTH_USER, value).apply()

    var islogin: Boolean
    get() = sp.getBoolean(IS_LOGIN, false)
    private set(value) = sp.edit().putBoolean(IS_LOGIN, value).apply()

    suspend fun login(email: String, password: String): ActionState<AuthUser>{
        val user = authUser
        return if (user == null){
            ActionState(message = "Anda Belum terdaftar", isSuccsess = false)
        }else if(email.isBlank() || password.isBlank()){
            ActionState(message = "Email dan password tidak boleh kosong", isSuccsess = false)
        }else if (user.email == email && user.password == password) {
            islogin = true
            ActionState(authUser, message = "Anda berhasil Login")
        }else {
            ActionState(message = "Email atau password salah", isSuccsess = false)
        }
    }

    suspend fun register(user: AuthUser): ActionState<AuthUser>{
        return if(user.email.isBlank() || user.password.isBlank()) {
            ActionState(message = "Email dan password tidak boleh kosong", isSuccsess = false)
        }else {
            authUser = user
            ActionState(user, message = "Anda berhasil daftar")
        }
    }

    suspend fun logout(): ActionState<Boolean>{
        islogin = false
        return ActionState(true, message = "Anda berhasil logout")
    }
}