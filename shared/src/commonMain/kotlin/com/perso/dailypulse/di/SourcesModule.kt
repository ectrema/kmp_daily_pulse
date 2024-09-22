package com.perso.dailypulse.di

import com.perso.dailypulse.data.repositories.SourcesRepository
import com.perso.dailypulse.data.repositories.impl.SourcesRepositoryImpl
import com.perso.dailypulse.data.services.SourcesService
import com.perso.dailypulse.domain.use_cases.SourcesUseCase
import com.perso.dailypulse.presentation.sources.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> {
        SourcesService(get())
    }
    
    single<SourcesRepository> {
        SourcesRepositoryImpl(get())
    }

    single<SourcesUseCase> {
        SourcesUseCase(get())
    }

    single<SourcesViewModel> {
        SourcesViewModel(get())
    }
}