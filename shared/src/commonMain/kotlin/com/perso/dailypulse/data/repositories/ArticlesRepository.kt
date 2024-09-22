package com.perso.dailypulse.data.repositories

import com.perso.dailypulse.data.responses.articles.ArticleRaw

abstract class ArticlesRepository {
    abstract suspend fun getArticles(forceRefresh: Boolean): List<ArticleRaw>
}