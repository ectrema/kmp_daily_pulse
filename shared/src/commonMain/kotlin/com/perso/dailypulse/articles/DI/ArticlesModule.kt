package com.perso.dailypulse.articles.DI

import com.perso.dailypulse.articles.ArticlesService
import com.perso.dailypulse.articles.ArticlesUseCase
import com.perso.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single<ArticlesViewModel> {
        ArticlesViewModel(get())
    }
}