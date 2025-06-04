package com.task.businesslogicshared

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak") // Context-ul este applicationContext, deci este considerat sigur aici.
internal object AppContextProvider {
    private var appContext: Context? = null

    fun initialize(context: Context) {
        // Folosim applicationContext pentru a evita memory leaks asociate cu Context-uri de Activity/Service.
        // Inițializează o singură dată pentru a preveni suprascrierea accidentală.
        if (appContext == null) {
            appContext = context.applicationContext
        }
    }

    fun getContext(): Context {
        return appContext ?: throw IllegalStateException(
            "AppContextProvider not initialized. " +
            "Ensure the AppContextInitializer (ContentProvider) is correctly declared " +
            "in your library's AndroidManifest.xml, or if you are in a test environment, " +
            "ensure you initialize it manually."
        )
    }
}
