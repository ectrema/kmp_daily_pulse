package com.perso.dailypulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.perso.dailypulse.Platform
import com.perso.dailypulse.android.screens.AboutScreen
import com.perso.dailypulse.android.screens.ArticlesScreen
import com.perso.dailypulse.articles.ArticlesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articlesViewModel: ArticlesViewModel by viewModels()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArticlesScreen(articlesViewModel = articlesViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        AboutScreen()
    }
}
