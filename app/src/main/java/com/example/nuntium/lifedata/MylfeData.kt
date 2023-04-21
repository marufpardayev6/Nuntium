package com.example.nuntium.lifedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nuntium.models.Article
import com.example.nuntium.models.News

class MylfeData {

    private val lifeData = MutableLiveData<News>()

    fun set(article: News) {
        lifeData.value = article
    }

    fun get(): LiveData<News> {
        return lifeData
    }

}