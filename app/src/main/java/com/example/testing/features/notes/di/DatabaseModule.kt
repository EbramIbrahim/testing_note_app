package com.example.testing.features.notes.di

import android.content.Context
import androidx.room.Room
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import com.example.testing.features.note_list.domain.usecase.DeleteNoteUC
import com.example.testing.features.note_list.domain.usecase.GetNoteListUC
import com.example.testing.features.notes.data.repository.NoteRepository
import com.example.testing.features.notes.data.repository.entity.NoteDatabase
import com.example.testing.features.notes.domain.repository.INoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDatabase: NoteDatabase
    ): INoteRepository {
        return NoteRepository(noteDatabase)
    }

    @Provides
    @Singleton
    fun provideGetNoteListUC(
        noteRepository: INoteRepository
    ): GetNoteListUC {
        return GetNoteListUC(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUC(
        noteRepository: INoteRepository
    ): DeleteNoteUC {
        return DeleteNoteUC(noteRepository)
    }


    @Provides
    @Singleton
    fun provideUpsertNoteUC(
        noteRepository: INoteRepository
    ): UpsertNoteUC {
        return UpsertNoteUC(noteRepository)
    }

}