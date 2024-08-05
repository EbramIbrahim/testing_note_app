package com.example.testing.common.data.repository

import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository

class FakeNoteRepository: INoteRepository {

    private var noteItems = mutableListOf<Note>()

    fun shouldHaveFilledList(shouldHaveFilledList: Boolean) {
        noteItems =
            if (shouldHaveFilledList) {
                mutableListOf(
                    Note(1, "d title 1", "desc 1", "image1", 12),
                    Note(2, "c title 1", "desc 2", "image2", 9),
                    Note(3, "b title 1", "desc 3", "image3", 3),
                    Note(4, "a title 1", "desc 4", "image4", 30)
                )
            } else {
                mutableListOf()
            }
    }

    override suspend fun upsertNote(note: Note) {
        noteItems.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteItems.remove(note)
    }

    override suspend fun getNoteList(): List<Note> {
        return noteItems
    }
}