package com.zeus.compose.ui.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeus.compose.domain.usecases.GetContentListUseCase
import com.zeus.compose.ui.viewEvents.ContentListEvents
import com.zeus.compose.ui.viewStates.ContentListState
import kotlinx.coroutines.launch

class ContentListViewModel(
    private val getContentListUseCase: GetContentListUseCase
): ViewModel() {

    private val _state = mutableStateOf(ContentListState())
    val state: State<ContentListState> = _state

    fun onEvent(event: ContentListEvents) {
        when(event) {
            is ContentListEvents.OnInitScreen -> {
                getContentList(event.contentName)
            }
        }
    }

    private fun getContentList(contentName: String) = viewModelScope.launch {
        getContentListUseCase.invoke(contentName).collect { contentList ->
            _state.value = _state.value.copy(
                contentList = contentList
            )
        }
    }
}