package com.example.whatsapphomepageclone

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

enum class TabStatus {
    CHATS,
    UPDATES,
    CALLS
}

data class UiState(
    val selectedTab: TabStatus = TabStatus.CHATS
)

fun initialState() = UiState()

class MainActivityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(initialState())
    val uiState: StateFlow<UiState> = _uiState

    fun onTabSelected(tabStatus: TabStatus) {
        _uiState.update {
            it.copy(selectedTab = tabStatus)
        }
    }
}