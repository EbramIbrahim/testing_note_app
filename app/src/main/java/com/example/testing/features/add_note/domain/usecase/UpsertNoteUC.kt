package com.example.testing.features.add_note.domain.usecase

import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository

class UpsertNoteUC(
    private val noteRepository: INoteRepository
) {

    suspend operator fun invoke(
        title: String,
        description: String,
        image: String
    ): Boolean {

        if (title.isEmpty() || description.isEmpty())
            return false

        val note = Note(
            title = title,
            description = description,
            imageUrl = image,
            noteDate = System.currentTimeMillis()
        )
        noteRepository.upsertNote(note)
        return true
    }
}








