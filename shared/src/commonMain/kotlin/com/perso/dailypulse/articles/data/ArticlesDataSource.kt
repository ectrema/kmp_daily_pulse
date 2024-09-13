package com.perso.dailypulse.articles.data

import com.benjamin.daily.pulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {
    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    private fun insertArticle(article: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            article.title,
            article.desc,
            article.date,
            article.imgUrl,
        )
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?,
    ): ArticleRaw = ArticleRaw(
        title, desc, date, url
    )
}