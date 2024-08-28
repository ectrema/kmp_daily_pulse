//
//  AbountScreen.swift
//  iosApp
//
//  Created by Benjamin Minel on 28/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AbountScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView().navigationTitle("About Device")
        }
    }
}

#Preview {
    AbountScreen()
}
