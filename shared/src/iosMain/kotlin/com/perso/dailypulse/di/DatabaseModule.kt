package com.perso.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.benjamin.daily.pulse.db.DailyPulseDatabase
import com.perso.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> {
        DatabaseDriverFactory().createDriver()
    }

    single<DailyPulseDatabase> {
        DailyPulseDatabase(get())
    }
}