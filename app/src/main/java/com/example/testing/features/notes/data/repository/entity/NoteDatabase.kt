package com.example.testing.features.notes.data.repository.entity

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao: NoteDao
}