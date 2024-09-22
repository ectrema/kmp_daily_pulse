package com.perso.dailypulse.domain.entities

data class SourceEntity(
    val id: String,

    val name: String,

    val desc: String?,

    val url: String,

    val category: String?,

    val language: String?,

    val country: String?,
)