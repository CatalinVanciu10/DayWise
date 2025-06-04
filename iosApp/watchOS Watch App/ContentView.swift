//
//  ContentView.swift
//  watchOS Watch App
//
//  Created by Catalin Vanciu on 04.06.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import businessLogicShared

struct ContentView: View {
    
    let platformName = Platform_watchosKt.platform()
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(platformName)
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
