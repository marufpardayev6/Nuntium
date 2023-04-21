package com.example.nuntium.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LanguageDB")
data class LanguageDB(
    val language: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
