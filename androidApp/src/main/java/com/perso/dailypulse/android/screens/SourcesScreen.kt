package com.perso.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.perso.dailypulse.domain.entities.SourceEntity
import com.perso.dailypulse.presentation.sources.SourcesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SourcesScreen(
    sourcesViewModel: SourcesViewModel = koinViewModel(),
) {
    val state = sourcesViewModel.sourcesState.collectAsState()

    Column {
        AppBar()
        if (state.value.error != null) ErrorBody(message = state.value.error!!)
        if (state.value.sources.isNotEmpty()) SourcesListView(sources = state.value.sources)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(title = { Text(text = "Sources") })
}

@Composable
private fun ErrorBody(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = message, style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)

        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SourcesListView(sources: List<SourceEntity>) {
    LazyColumn {
        items(sources) { item ->
            SourceItem(source = item)
        }
    }
}

@Composable
private fun SourceItem(source: SourceEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        source.desc?.let {
            Text(
                text = it,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.language + " " + source.country,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}