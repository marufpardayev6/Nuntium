package com.example.nuntium.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ForLanguages")
data class ForLanguages(
    val tongue: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
