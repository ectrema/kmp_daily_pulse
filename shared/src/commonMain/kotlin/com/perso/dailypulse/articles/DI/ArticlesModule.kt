package com.perso.dailypulse.articles.di

import com.perso.dailypulse.articles.data.ArticlesDataSource
import com.perso.dailypulse.articles.data.ArticlesRepository
import com.perso.dailypulse.articles.data.ArticlesService
import com.perso.dailypulse.articles.domain.ArticlesUseCase
import com.perso.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesDataSource> {
        ArticlesDataSource(get())
    }
    single<ArticlesRepository> {
        ArticlesRepository(get(), get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single<ArticlesViewModel> {
        ArticlesViewModel(get())
    }
}