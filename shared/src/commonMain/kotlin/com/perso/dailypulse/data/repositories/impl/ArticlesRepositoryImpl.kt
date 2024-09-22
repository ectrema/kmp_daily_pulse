package com.perso.dailypulse.data.repositories.impl

import com.perso.dailypulse.data.data_sources.ArticlesDataSource
import com.perso.dailypulse.data.repositories.ArticlesRepository
import com.perso.dailypulse.data.responses.articles.ArticleRaw
import com.perso.dailypulse.data.services.ArticlesService

class ArticlesRepositoryImpl(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService,
) : ArticlesRepository() {
    override suspend fun getArticles(forceRefresh: Boolean): List<ArticleRaw> {
        val articlesDb = dataSource.getAllArticles()

        println("${articlesDb.size} from DB")

        if (articlesDb.isEmpty() || forceRefresh) {
            val fetchedArticle = service.fetchArticles()
            dataSource.insertArticles(fetchedArticle)
            return fetchedArticle
        }

        return articlesDb
    }
}