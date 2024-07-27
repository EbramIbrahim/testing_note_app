package com.example.testing.features.note_list.domain.usecase

import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository

class DeleteNoteUC(
    private val noteRepository: INoteRepository
) {


    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}