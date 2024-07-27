package com.example.testing.features.notes.domain.repository

import com.example.testing.features.notes.domain.models.Note

interface INoteRepository {

    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteList(): List<Note>
}