package com.perso.dailypulse.articles.presentation

import com.perso.dailypulse.articles.domain.ArticleEntity

data class ArticlesState(
    val articles: List<ArticleEntity> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)