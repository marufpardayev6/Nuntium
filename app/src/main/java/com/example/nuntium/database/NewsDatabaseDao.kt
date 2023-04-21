package com.example.nuntium.database

import androidx.room.*

@Dao
interface NewsDatabaseDao {

    @Insert
    fun addNews(news: NewsDatabase)

    @Query("select * from news")
    fun getAllNews(): List<NewsDatabase>

    @Update
    fun updateNews(news: NewsDatabase)

    @Delete
    fun deleteNews(news: NewsDatabase)

    @Insert
    fun addLG(languageDB: LanguageDB)

    @Delete
    fun deletLG(languageDB: LanguageDB)

    @Update
    fun updateLG(languageDB: LanguageDB)

    @Query("select * from LanguageDB")
    fun getAllLG(): List<LanguageDB>

    @Insert
    fun addForLG(forLanguages: ForLanguages)

    @Delete
    fun deletForLG(forLanguages: ForLanguages)

    @Update
    fun updateForLG(forLanguages: ForLanguages)

    @Query("select * from ForLanguages")
    fun getForAllLG(): List<ForLanguages>

}
