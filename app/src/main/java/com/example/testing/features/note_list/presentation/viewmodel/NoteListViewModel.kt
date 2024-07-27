package com.example.testing.features.note_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testing.features.note_list.domain.usecase.DeleteNoteUC
import com.example.testing.features.note_list.domain.usecase.GetNoteListUC
import com.example.testing.features.notes.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNoteListUC: GetNoteListUC,
    private val deleteNoteUC: DeleteNoteUC
): ViewModel() {

    private val _noteListState: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val noteListState = _noteListState.asStateFlow()


    private val _orderByTitleState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val orderByTitleState = _orderByTitleState.asStateFlow()


    fun loadNote() {
        viewModelScope.launch {
            _noteListState.update {
                getNoteListUC.invoke(_orderByTitleState.value)
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUC.invoke(note)
            loadNote()
        }

    }

    fun changeNoteOrder() {
        viewModelScope.launch {
            _orderByTitleState.update { it.not() }
        }
    }

}





















