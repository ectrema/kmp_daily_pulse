import SwiftUI
import shared

struct ContentView: View {
    
    @State private var shouldOpenAbout: Bool = false
    
    var body: some View {
        NavigationStack {
            ArticlesScreen(viewModel: .init())
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
                }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
