package com.perso.dailypulse.presentation.articles

import com.perso.dailypulse.BaseViewModel
import com.perso.dailypulse.domain.use_cases.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticle()
    }

    fun getArticle(forceRefresh: Boolean = false) {
        scope.launch {
            _articlesState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )

            _articlesState.emit(
                ArticlesState(
                    articles = useCase.getArticles(forceRefresh), loading = false,
                )
            )
        }
    }
}