package com.example.testing.features.notes.di

import android.content.Context
import androidx.room.Room
import com.example.testing.repository.FakeImageRepository
import com.example.testing.common.data.repository.FakeNoteRepository
import com.example.testing.common.presentation.util.Constant.BASE_URL
import com.example.testing.features.add_note.data.repository.remote.ImageApi
import com.example.testing.features.add_note.domain.repository.IImageRepository
import com.example.testing.features.add_note.domain.usecase.SearchImagesUC
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import com.example.testing.features.note_list.domain.usecase.DeleteNoteUC
import com.example.testing.features.note_list.domain.usecase.GetNoteListUC
import com.example.testing.features.notes.data.repository.entity.NoteDatabase
import com.example.testing.features.notes.domain.repository.INoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
    ): INoteRepository {
        return FakeNoteRepository()
    }

    @Provides
    @Singleton
    fun provideGetAllNotesUseCase(
        noteRepository: INoteRepository
    ): GetNoteListUC {
        return GetNoteListUC(noteRepository)
    }


    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
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

    @Provides
    @Singleton
    fun provideImageRepository(
    ): IImageRepository {
        return FakeImageRepository()
    }

    @Provides
    @Singleton
    fun provideSearchImagesUC(
        imageRepository: IImageRepository
    ): SearchImagesUC {
        return SearchImagesUC(imageRepository)
    }

}