package com.perso.dailypulse.articles

import com.perso.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticle()
    }

    private fun getArticle() {
        scope.launch {
            delay(1500)
            _articlesState.emit(
                ArticlesState(
                    articles = fetchArticles()
                )
            )
        }
    }

    private suspend fun fetchArticles(): List<Article> {
        return listOf(
            Article(
                title = "title",
                desc = "description",
                date = "2024-08-29",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwyXeKDN29AmZgZPLS7n0Bepe8QmVappBwZCeA3XWEbWNdiDFB"
            )
        )
    }
}