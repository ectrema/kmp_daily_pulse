package com.perso.dailypulse.articles

import com.perso.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    private val useCase: ArticlesUseCase

    init {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true

                })
            }
        }

        val service= ArticlesService(client)

        useCase = ArticlesUseCase(service)

        getArticle()
    }

    private fun getArticle() {
        scope.launch {
            _articlesState.emit(
                ArticlesState(
                    articles = useCase.getArticles()
                )
            )
        }
    }
}