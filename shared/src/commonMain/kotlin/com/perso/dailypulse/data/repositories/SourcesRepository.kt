package com.perso.dailypulse.data.repositories

import com.perso.dailypulse.data.responses.sources.SourcesRaw

abstract class SourcesRepository {
    abstract suspend fun getSources(): List<SourcesRaw>
}