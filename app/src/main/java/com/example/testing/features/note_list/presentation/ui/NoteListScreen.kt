package com.example.testing.features.note_list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testing.R
import com.example.testing.common.presentation.util.Constant
import com.example.testing.features.note_list.presentation.viewmodel.NoteListViewModel

@Composable
fun NoteListScreen(
    onNavigate: () -> Unit,
    noteListViewModel: NoteListViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        noteListViewModel.loadNote()
    }

    val noteListState by noteListViewModel.noteListState.collectAsState()
    val noteOrderState by noteListViewModel.orderByTitleState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.notes, noteListState.size),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp
                )

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            noteListViewModel.changeNoteOrder()
                        }
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = if (noteOrderState) stringResource(R.string.t) else stringResource(R.string.d),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = "")
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate() },
                modifier = Modifier.testTag(Constant.ADD_NOTE_FAB)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_a_note)
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(
                count = noteListState.size,
                key = { it }
            ) { index ->
                NoteListItem(
                    onDelete = { noteListViewModel.deleteNote(noteListState[index]) },
                    note = noteListState[index]
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }

    }
}





