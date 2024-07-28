package com.example.testing.features.add_note.presentation.viewmodel

data class AddNoteState (
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",

    val isImageDialogVisible: Boolean = false,
    val isLoadingImages: Boolean = false,

    val imageList: List<String> = emptyList(),
    val searchQuery: String = ""
)