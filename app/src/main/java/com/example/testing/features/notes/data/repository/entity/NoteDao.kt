package com.example.testing.features.notes.data.repository.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    suspend fun getNoteEntities(): List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)


}









