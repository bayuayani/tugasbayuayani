package com.example.tugas7.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas7.data.model.ActionState
import com.example.tugas7.data.model.News
import com.example.tugas7.data.repository.NewsRepository
import com.example.tugas7.ui.home.MainActivity
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val parent: MainActivity by lazy{ actionState as MainActivity}
    private val repo: NewsRepository by lazy { NewsRepository() }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<News>>()
    val detailResp = MutableLiveData<News>()
    val searchResp = MutableLiveData<List<News>>()

    val url = MutableLiveData("")
    val query = MutableLiveData( "")

    fun listNews(){
        viewModelScope.launch {
            loading.value = true
            val resp = repo.listNews()
            actionState.value = resp
            listResp.value = resp.data
            loading.value = false
        }
    }

    fun detailNews(url: String? = this.url.value){
        viewModelScope.launch {
            loading.value = true
            val it = ""
            val resp = repo.detailNews(it)
            actionState.value = resp
            detailResp.value = resp.data
            loading.value = false
        }
    }

    fun searchNews(query: String? = this.query.value){
        viewModelScope.launch {
            loading.value = true
            val it = ""
            val resp = repo.searchNews(it)
            actionState.value = resp
            searchResp.value = resp.data
            loading.value = false
        }
    }
}