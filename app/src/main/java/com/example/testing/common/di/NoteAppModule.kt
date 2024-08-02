package com.example.testing.common.di

import android.content.Context
import androidx.room.Room
import com.example.testing.common.presentation.util.Constant.BASE_URL
import com.example.testing.features.add_note.data.repository.ImageRepository
import com.example.testing.features.add_note.data.repository.remote.ImageApi
import com.example.testing.features.add_note.domain.repository.IImageRepository
import com.example.testing.features.add_note.domain.usecase.SearchImagesUC
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

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

    @Provides
    @Singleton
    fun provideImageRepository(
        api: ImageApi
    ): IImageRepository {
        return ImageRepository(api)
    }

    @Provides
    @Singleton
    fun provideSearchImagesNoteUC(
        imageRepository: IImageRepository
    ): SearchImagesUC {
        return SearchImagesUC(imageRepository)
    }

}