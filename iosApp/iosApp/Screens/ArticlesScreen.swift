//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Benjamin Minel on 29/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticlesScreen {
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticlesViewModel
        
        init() {
            articlesViewModel = ArticlesInjector().articlesViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState: ArticlesState
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    @ObservedObject private(set) var viewModel : ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            if viewModel.articlesState.loading {
                Loader()
            }
            if let error = viewModel.articlesState.error{
                ErrorBody(message: error)
            }
            if (!viewModel.articlesState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articlesState.articles, id: \.self) { article in
                            ArticleItem(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorBody: View {
    var message: String
    var body: some View {
        Text(message).font(.title)
    }
}

struct ArticleItem: View {
    var article: ArticleEntity
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fit).cornerRadius(12)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                    
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
        
    }
}

