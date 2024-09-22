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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.perso.dailypulse.domain.entities.ArticleEntity
import com.perso.dailypulse.presentation.articles.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    onAboutClick: () -> Unit,
    onSourcesClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = koinViewModel(),
) {
    val state = articlesViewModel.articlesState.collectAsState()

    Column {
        AppBar(onAboutClick, onSourcesClick)
        if (state.value.error != null) ErrorBody(message = state.value.error!!)
        if (state.value.articles.isNotEmpty()) ArticlesListView(viewModel = articlesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutClick: () -> Unit,
    onSourcesClick: () -> Unit,
) {
    TopAppBar(title = { Text(text = "Articles") }, actions = {
        IconButton(onClick = onAboutClick) {
            Icon(
                imageVector = Icons.Outlined.Info, contentDescription = "About Device"
            )
        }
        IconButton(onClick = onSourcesClick) {
            Icon(
                imageVector = Icons.Outlined.Check, contentDescription = "About Device"
            )
        }
    })
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
private fun ArticlesListView(viewModel: ArticlesViewModel) {
    val pullRefreshState =
        rememberPullRefreshState(refreshing = viewModel.articlesState.value.loading,
            onRefresh = { viewModel.getArticle(true) })
    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        LazyColumn {
            items(viewModel.articlesState.value.articles) { article ->
                ArticlesItem(article = article)
            }
        }
        PullRefreshIndicator(
            refreshing = viewModel.articlesState.value.loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = if (viewModel.articlesState.value.loading) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun ArticlesItem(article: ArticleEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        AsyncImage(
            model = article.imageUrl, contentDescription = null, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.desc,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}