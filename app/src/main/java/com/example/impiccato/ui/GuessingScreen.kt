package com.example.impiccato.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object GuessingScreenDestination : NavigationDestination {
    override val route = "guessing_screen"
    override val idArgs = "gameId"
    override val routeWithArgs = "$route/${idArgs}"
}
@Composable
fun GuessingScreen(
    modifier: Modifier = Modifier
) {

}