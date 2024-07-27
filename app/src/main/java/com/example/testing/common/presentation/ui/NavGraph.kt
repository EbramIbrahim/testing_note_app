package com.example.testing.common.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.testing.features.note_list.presentation.ui.NoteListScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.NoteList
    ) {
        composable<Screen.NoteList> {
            NoteListScreen(onNavigate = {
                navController.navigate(Screen.AddNote)
            }
            )
        }

    }
}








