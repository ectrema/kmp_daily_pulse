package com.perso.dailypulse.di

import com.perso.dailypulse.articles.DI.articlesModule

val sharedKoinModule = listOf(
    articlesModule,
    networkModule,
)