package com.example.impiccato.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.impiccato.model.Game
import com.example.impiccato.R

object HomeScreenDestination : NavigationDestination {
    override val route = "home"
    override val idArgs = String()
    override val routeWithArgs = route
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pastGames: List<Game>,
    onStartPlaying: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            StartPlayingButton(
                onClick = onStartPlaying
            )
        }
    ) { paddingValues ->
       Column(
           modifier = modifier
               .padding(paddingValues),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           if (pastGames.isEmpty()) {
               Text(
                   text = stringResource(id = R.string.first_time_message),
                   textAlign = TextAlign.Justify,
                   style = MaterialTheme.typography.headlineMedium
               )
           } else {
               PastGamesList(pastGames = pastGames)
           }
       }
    }
}

@Composable
fun StartPlayingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Gamepad, 
            contentDescription = null
        )
    }
}

@Composable
fun PastGamesList(
    modifier: Modifier = Modifier,
    pastGames: List<Game>
) {
    LazyColumn {
        
    }
}