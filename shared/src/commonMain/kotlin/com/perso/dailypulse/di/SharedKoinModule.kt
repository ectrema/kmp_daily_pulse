package com.perso.dailypulse.di

import com.perso.dailypulse.articles.di.articlesModule

val sharedKoinModule = listOf(
    articlesModule,
    networkModule,
)