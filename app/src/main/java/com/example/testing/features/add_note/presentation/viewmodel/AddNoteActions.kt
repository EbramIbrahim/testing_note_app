package com.example.testing.features.add_note.presentation.viewmodel

sealed interface AddNoteActions {

    data class UpdateTitle(val title: String): AddNoteActions

    data class UpdateDescription(val description: String): AddNoteActions

    data class UpdateSearchQuery(val query: String): AddNoteActions

    data class PickImage(val imageUrl: String): AddNoteActions

    data object UpdateImageDialogVisibility: AddNoteActions

    data object SaveNote: AddNoteActions

}











