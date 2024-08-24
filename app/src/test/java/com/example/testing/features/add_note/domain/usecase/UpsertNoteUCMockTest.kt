package com.example.testing.features.add_note.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.features.notes.domain.repository.INoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class UpsertNoteUCMockTest {


    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

     private val noteRepository = mock<INoteRepository>()

    private lateinit var upsertNoteUC: UpsertNoteUC

    @Before
    fun setup() {
        upsertNoteUC = UpsertNoteUC(noteRepository)
    }

    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = upsertNoteUC.invoke(
            "",
            "description",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = upsertNoteUC.invoke(
            "title",
            "",
            "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty image, return true`() = runTest {
        val isInserted = upsertNoteUC.invoke(
            "title",
            "description",
            ""
        )

        assertThat(isInserted).isTrue()
    }

    @Test
    fun `upsert note with a valid inputs, return true`() = runTest {
        val isInserted = upsertNoteUC.invoke(
            "title",
            "description",
            "image"
        )

        assertThat(isInserted).isTrue()
    }




}









