package com.perso.dailypulse.presentation.sources

import com.perso.dailypulse.domain.entities.SourceEntity

data class SourcesState(
    val sources: List<SourceEntity> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)