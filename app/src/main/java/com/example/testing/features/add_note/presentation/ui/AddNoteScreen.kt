package com.example.testing.features.add_note.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.testing.R
import com.example.testing.common.presentation.util.Constant
import com.example.testing.features.add_note.presentation.viewmodel.AddNoteActions
import com.example.testing.features.add_note.presentation.viewmodel.AddNoteViewModel

@Composable
fun AddNoteScreen(
    onSave: () -> Unit,
    addNoteViewModel: AddNoteViewModel = hiltViewModel()
) {
    val addNoteState by addNoteViewModel.addNoteState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        addNoteViewModel.noteSavedChannel.collect { isSaved ->
            if (isSaved) {
                onSave()
            } else {
                Toast.makeText(
                    context,
                    R.string.error_saving_note,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .clickable { addNoteViewModel.onAction(AddNoteActions.UpdateImageDialogVisibility) }
                .testTag(Constant.NOTE_IMAGE + addNoteState.imageUrl),
            model = ImageRequest
                .Builder(context)
                .data(addNoteState.imageUrl)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = addNoteState.description,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .testTag(Constant.TITLE_TEXT_FIELD),
            value = addNoteState.title,
            onValueChange = {
                addNoteViewModel.onAction(AddNoteActions.UpdateTitle(it))
            },
            label = {
                Text(text = stringResource(id = R.string.title))
            },
            maxLines = 4
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .testTag(Constant.DESCRIPTION_TEXT_FIELD),
            value = addNoteState.description,
            onValueChange = {
                addNoteViewModel.onAction(AddNoteActions.UpdateDescription(it))
            },
            label = {
                Text(text = stringResource(id = R.string.description))
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag(Constant.SAVE_BUTTON),
            onClick = { addNoteViewModel.onAction(AddNoteActions.SaveNote) }
        ) {
            Text(text = stringResource(id = R.string.save), fontSize = 17.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    if (addNoteState.isImageDialogVisible) {
        Dialog(onDismissRequest = {
            addNoteViewModel.onAction(AddNoteActions.UpdateImageDialogVisibility)
        }
        ) {
            ImageDialogContent(
                addNoteState = addNoteState,
                onSearchQueryChanged = {
                    addNoteViewModel.onAction(AddNoteActions.UpdateSearchQuery(it))
                },
                onImageClick = {
                    addNoteViewModel.onAction(AddNoteActions.PickImage(it))
                }
            )
        }
    }
}







