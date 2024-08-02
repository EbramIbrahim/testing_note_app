package com.example.testing.features.add_note.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.data.repository.FakeImageRepository
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import com.example.testing.common.data.repository.FakeNoteRepository
import com.example.testing.features.add_note.domain.usecase.SearchImagesUC
import com.example.testing.features.note_list.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AddNoteViewModelTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()


    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var fakeImageRepository: FakeImageRepository
    private lateinit var addNoteViewModel: AddNoteViewModel

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        fakeImageRepository = FakeImageRepository()
        val upsertNoteUC = UpsertNoteUC(fakeNoteRepository)
        val searchImageUc = SearchImagesUC(fakeImageRepository)
        addNoteViewModel = AddNoteViewModel(upsertNoteUC, searchImageUc)
    }


    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            "",
            "description",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            "title",
            "",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty image, return true`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            "title",
            "description",
            ""
        )

        assertThat(isInserted).isTrue()
    }

    @Test
    fun `upsert a valid note, return true`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            "title",
            "description",
            "image"
        )

        assertThat(isInserted).isTrue()
    }

    @Test
    fun `search images with invalid query, return error`() = runTest {
        addNoteViewModel.searchImages("")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()
        assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }

    @Test
    fun `search images with a valid query, return network error`() = runTest {
        fakeImageRepository.setShouldReturnError(true)
        addNoteViewModel.searchImages("query")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }

    @Test
    fun `search images with a valid query, return success`() = runTest {
        addNoteViewModel.searchImages("query")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList.isNotEmpty()).isTrue()
    }




}
















