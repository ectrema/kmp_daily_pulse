package com.perso.dailypulse.articles

data class ArticlesState(
    val articles: List<ArticleEntity> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)