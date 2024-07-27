package com.example.testing.features.notes.data.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val imageUrl: String,
    val noteDate: Long
)
