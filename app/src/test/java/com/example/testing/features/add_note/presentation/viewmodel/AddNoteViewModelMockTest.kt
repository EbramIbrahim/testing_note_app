package com.example.testing.features.add_note.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testing.common.presentation.util.Resource
import com.example.testing.features.add_note.domain.model.Image
import com.example.testing.features.add_note.domain.repository.IImageRepository
import com.example.testing.features.add_note.domain.usecase.SearchImagesUC
import com.example.testing.features.add_note.domain.usecase.UpsertNoteUC
import com.example.testing.features.note_list.MainCoroutineRule
import com.example.testing.features.notes.domain.repository.INoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AddNoteViewModelMockTest {

    @get:Rule
    var instantTaskEntity = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val upsertNoteUC = mock<UpsertNoteUC>()
    private val searchImagesUC = mock<SearchImagesUC>()

    private lateinit var addNoteViewModel: AddNoteViewModel

    @Before
    fun setup() {
        addNoteViewModel = AddNoteViewModel(upsertNoteUC, searchImagesUC)
    }


    @Test
    fun `search images with invalid query, return error`() = runTest {
        whenever(searchImagesUC.invoke("")).thenReturn(flowOf(Resource.Error()))

        addNoteViewModel.searchImages("")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }

    @Test
    fun `search images with a valid query, return network error`() = runTest {
        //Arrange
        whenever(searchImagesUC.invoke("")).thenReturn(flowOf(Resource.Error()))
        //Act
        addNoteViewModel.searchImages("query")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        //Assert
        assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }

    @Test
    fun `search images with a valid query, return success`() = runTest {
        // Arrange
        val image = Image(listOf("image1", "image2"))
        whenever(searchImagesUC.invoke("query")).thenReturn(flowOf(Resource.Success(image)))

        // Act
        addNoteViewModel.searchImages("query")
        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        //Assert
        assertThat(addNoteViewModel.addNoteState.value.imageList.isNotEmpty()).isTrue()
        assertThat(addNoteViewModel.addNoteState.value.imageList).isEqualTo(image.images)
    }

}











