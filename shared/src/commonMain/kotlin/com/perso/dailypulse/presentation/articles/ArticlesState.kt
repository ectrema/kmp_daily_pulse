package com.perso.dailypulse.presentation.articles

import com.perso.dailypulse.domain.entities.ArticleEntity

data class ArticlesState(
    val articles: List<ArticleEntity> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)