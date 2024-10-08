package com.perso.dailypulse.di

import com.perso.dailypulse.presentation.articles.SourcesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedKoinModule + databaseModule

    startKoin {
        modules(modules)
    }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: SourcesViewModel by inject()
}