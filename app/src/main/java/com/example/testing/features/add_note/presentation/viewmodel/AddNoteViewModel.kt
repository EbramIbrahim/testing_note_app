package com.example.testing.features.add_note.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.common.presentation.util.Resource
import com.example.testing.features.add_note.domain.usecase.SearchImagesUC
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val upsertNoteUc: UpsertNoteUC,
    private val searchImagesUC: SearchImagesUC
) : ViewModel() {


    private val _addNoteState: MutableStateFlow<AddNoteState> = MutableStateFlow(AddNoteState())
    val addNoteState = _addNoteState.asStateFlow()

    private val _noteSavedChannel: Channel<Boolean> = Channel()
    val noteSavedChannel = _noteSavedChannel.receiveAsFlow()


    fun onAction(action: AddNoteActions) {
        when (action) {
            is AddNoteActions.UpdateTitle -> {
                _addNoteState.update { it.copy(title = action.title) }
            }

            is AddNoteActions.UpdateDescription -> {
                _addNoteState.update { it.copy(description = action.description) }
            }

            is AddNoteActions.UpdateSearchQuery -> {
                _addNoteState.update { it.copy(searchQuery = action.query) }
                searchImages(action.query)
            }

            is AddNoteActions.PickImage -> {
                _addNoteState.update { it.copy(imageUrl = action.imageUrl) }
            }

            AddNoteActions.UpdateImageDialogVisibility -> {
                _addNoteState.update { it.copy(isImageDialogVisible = !it.isImageDialogVisible) }
            }

            AddNoteActions.SaveNote -> {
                viewModelScope.launch {
                    val isSaved = upsertNote(
                        title = _addNoteState.value.title,
                        description = _addNoteState.value.description,
                        imageUrl = _addNoteState.value.imageUrl
                    )
                    _noteSavedChannel.send(isSaved)
                }
            }
        }
    }


    suspend fun upsertNote(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {
        return upsertNoteUc.invoke(
            title, description, imageUrl
        )
    }


    private var searchJob: Job? = null
    fun searchImages(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            searchImagesUC.invoke(query)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _addNoteState.update { it.copy(imageList = emptyList()) }
                        }

                        is Resource.Loading -> {
                            _addNoteState.update { it.copy(isLoadingImages = result.isLoading) }
                        }

                        is Resource.Success -> {
                            _addNoteState.update {
                                it.copy(
                                    imageList = result.data?.images ?: emptyList()
                                )
                            }
                        }
                    }
                }
        }
    }
}





































