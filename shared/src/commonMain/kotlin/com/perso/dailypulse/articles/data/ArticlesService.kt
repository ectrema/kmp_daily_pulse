package com.perso.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val client: HttpClient) {

    private val country = "us"
    private val apiKey = "bae27ae489f648e298b1dcc1b3fe466f"
    private val category = "business"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse =
            client.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()

        return response.articles
    }
}