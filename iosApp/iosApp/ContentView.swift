import SwiftUI
import shared

struct ContentView: View {
    
    @State private var shouldOpenAbout: Bool = false
    
    var body: some View {
        let articlesScreen = ArticlesScreen(viewModel: .init())
        
        NavigationStack {
            ArticlesScreen(viewModel: .init()).navigationTitle("Article")
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout=true
                        } label: {
                            Label("About", systemImage: "info.circle" ).labelStyle(.titleAndIcon)
                        }.popover(isPresented: $shouldOpenAbout) {
                            AbountScreen()
                        }
                    }
                }.refreshable {
                    articlesScreen.viewModel.articlesViewModel.getArticle(forceRefresh: true)
                }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
