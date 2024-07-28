package com.example.testing.features.add_note.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import com.example.testing.features.note_list.MainCoroutineRule
import com.example.testing.features.note_list.domain.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AddNoteViewModelTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()


    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var addNoteViewModel: AddNoteViewModel

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        val upsertNoteUC = UpsertNoteUC(fakeNoteRepository)
        addNoteViewModel = AddNoteViewModel(upsertNoteUC)
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




}
















