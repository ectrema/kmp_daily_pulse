package com.perso.dailypulse.android.di

import com.perso.dailypulse.presentation.articles.ArticlesViewModel
import com.perso.dailypulse.presentation.sources.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ArticlesViewModel(get())
        SourcesViewModel(get())
    }
}