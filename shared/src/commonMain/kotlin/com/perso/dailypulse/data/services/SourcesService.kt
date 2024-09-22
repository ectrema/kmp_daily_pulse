package com.perso.dailypulse.data.services

import com.perso.dailypulse.data.responses.sources.SourcesRaw
import com.perso.dailypulse.data.responses.sources.SourcesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val client: HttpClient) {
    private val apiKey = "bae27ae489f648e298b1dcc1b3fe466f"

    suspend fun fetchSources(): List<SourcesRaw> {
        val response: SourcesResponse =
            client.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey")
                .body()

        return response.sources
    }
}