package com.example.testing.features.note_list.domain.usecase

import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository

class GetNoteListUC(
    private val noteRepository: INoteRepository
) {


    suspend operator fun invoke(isOrderedByTitle: Boolean): List<Note> {
        return if (isOrderedByTitle) {
            noteRepository.getNoteList().sortedBy { it.title.lowercase() }
        } else {
            noteRepository.getNoteList().sortedBy { it.noteDate }
        }
    }
}