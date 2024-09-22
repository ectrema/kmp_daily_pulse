package com.perso.dailypulse.data.repositories.impl

import com.perso.dailypulse.data.repositories.SourcesRepository
import com.perso.dailypulse.data.responses.sources.SourcesRaw
import com.perso.dailypulse.data.services.SourcesService

class SourcesRepositoryImpl(
    private val service: SourcesService,
) : SourcesRepository() {
    override suspend fun getSources(): List<SourcesRaw> {
        val fetchedArticle = service.fetchSources()
        return fetchedArticle
    }
}