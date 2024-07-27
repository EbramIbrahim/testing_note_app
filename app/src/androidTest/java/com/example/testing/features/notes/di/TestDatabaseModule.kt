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
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideGetAllNotesUseCase(
        noteRepository: NoteRepository
    ): GetNoteListUC {
        return GetNoteListUC(noteRepository)
    }


    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
        noteRepository: NoteRepository
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