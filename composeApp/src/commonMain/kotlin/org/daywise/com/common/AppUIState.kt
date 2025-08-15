package org.daywise.com.common

sealed class AppUIState {
    object Loading : AppUIState()
    object Success : AppUIState()
    data class Error(val message: String) : AppUIState()
}
