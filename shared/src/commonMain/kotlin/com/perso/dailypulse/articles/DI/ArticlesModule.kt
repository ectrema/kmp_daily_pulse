package com.perso.dailypulse.articles.di

import com.perso.dailypulse.articles.ArticlesDataSource
import com.perso.dailypulse.articles.ArticlesRepository
import com.perso.dailypulse.articles.ArticlesService
import com.perso.dailypulse.articles.ArticlesUseCase
import com.perso.dailypulse.articles.ArticlesViewModel
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