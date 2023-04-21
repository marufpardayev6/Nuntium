package com.example.nuntium.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsDatabase(
    var author: String? = null,
    var content: String? = null,
    var description: String? = null,
    var publishedAt: String? = null,
    var title: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var databse: Boolean? = false,
    @PrimaryKey
    var id: Long? = null,
) : java.io.Serializable
