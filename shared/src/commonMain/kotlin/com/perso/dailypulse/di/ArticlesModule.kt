package com.perso.dailypulse.di

import com.perso.dailypulse.data.data_sources.ArticlesDataSource
import com.perso.dailypulse.data.repositories.ArticlesRepository
import com.perso.dailypulse.data.repositories.impl.ArticlesRepositoryImpl
import com.perso.dailypulse.data.services.ArticlesService
import com.perso.dailypulse.domain.use_cases.ArticlesUseCase
import com.perso.dailypulse.presentation.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> {
        ArticlesService(get())
    }

    single<ArticlesDataSource> {
        ArticlesDataSource(get())
    }
    single<ArticlesRepository> {
        ArticlesRepositoryImpl(get(), get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(get())
    }

    single<ArticlesViewModel> {
        ArticlesViewModel(get())
    }
}