package com.perso.dailypulse.domain.use_cases

import com.perso.dailypulse.data.repositories.SourcesRepository
import com.perso.dailypulse.data.responses.sources.SourcesRaw
import com.perso.dailypulse.domain.entities.SourceEntity

class SourcesUseCase(private val sourcesRepository: SourcesRepository) {

    suspend fun getSources(): List<SourceEntity> {
        val sourcesRaw = sourcesRepository.getSources()
        return mapArticles(sourcesRaw)
    }

    private fun mapArticles(sourcesRaw: List<SourcesRaw>): List<SourceEntity> =
        sourcesRaw.map { raw ->
            SourceEntity(
                name = raw.name,
                desc = raw.desc,
                id = raw.id,
                url = raw.url,
                category = raw.category,
                language = raw.language,
                country = raw.country
            )
        }


}