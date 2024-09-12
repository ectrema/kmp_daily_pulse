package com.perso.dailypulse.articles

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService,
) {
    suspend fun getArticles(): List<ArticleRaw> {
        val articlesDb = dataSource.getAllArticles()

        println("${articlesDb.size} from DB")

        if (articlesDb.isEmpty()) {
            val fetchedArticle = service.fetchArticles()
            dataSource.insertArticles(fetchedArticle)
            return fetchedArticle
        }

        return articlesDb
    }
}