package com.task.businesslogicshared

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration

actual fun platform(): String {
    val appContext: Context = AppContextProvider.getContext()
    val uiModeManager = appContext.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    return if (uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_WATCH) {
        "Wear OS (KMP)"
    } else {
        "Android (KMP)"
    }
}
