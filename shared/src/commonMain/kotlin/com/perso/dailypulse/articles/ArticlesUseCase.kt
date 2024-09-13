package com.perso.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val articlesRepository: ArticlesRepository) {

    suspend fun getArticles(forceRefresh: Boolean): List<ArticleEntity> {
        val articlesRaw = articlesRepository.getArticles(forceRefresh)
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw>): List<ArticleEntity> =
        articleRaw.map { raw ->
            ArticleEntity(
                title = raw.title,
                date = convertDate(raw.date),
                desc = raw.desc ?: "No description",
                imageUrl = raw.imgUrl
                    ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwyXeKDN29AmZgZPLS7n0Bepe8QmVappBwZCeA3XWEbWNdiDFB",
            )
        }

    private fun convertDate(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}