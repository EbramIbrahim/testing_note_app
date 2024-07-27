package com.example.testing.features.notes.data.repository

import com.example.testing.features.notes.data.mapper.NoteMapper
import com.example.testing.features.notes.data.repository.entity.NoteDatabase
import com.example.testing.features.notes.domain.models.Note
import com.example.testing.features.notes.domain.repository.INoteRepository

class NoteRepository(
    noteDatabase: NoteDatabase
): INoteRepository {

    private val dao = noteDatabase.noteDao
    override suspend fun upsertNote(note: Note) {
        dao.upsertNoteEntity(NoteMapper.domainToEntityForUpsert(note))
    }

    override suspend fun deleteNote(note: Note) {
        dao.upsertNoteEntity(NoteMapper.domainToEntityForDelete(note))
    }

    override suspend fun getNoteList(): List<Note> {
        return NoteMapper.entityToDomain(dao.getNoteEntities())
    }
}