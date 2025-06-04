package com.task.businesslogicshared

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

// Acest ContentProvider este folosit exclusiv pentru a obține Context-ul aplicației
// la pornire și a-l pasa către AppContextProvider.
// Nu va gestiona interogări de date reale.
internal class AppContextInitializer : ContentProvider() {

    override fun onCreate(): Boolean {
        // 'context' este non-null în interiorul metodei onCreate() a unui ContentProvider.
        // Inițializează AppContextProvider cu context-ul aplicației.
        context?.let { AppContextProvider.initialize(it) }
        // Returnează 'true' pentru a indica faptul că provider-ul a fost încărcat cu succes.
        // Dacă returnezi 'false', aplicația ar putea crăpa la pornire.
        return true
    }

    // Implementări goale (stub) pentru celelalte metode abstracte ale ContentProvider,
    // deoarece acest provider nu este destinat interacțiunilor cu date.
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}
