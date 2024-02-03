package com.example.impiccato.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.impiccato.R

@Composable
fun GameApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { GameTopBar()}
    ) {paddingValues ->
        val navController = rememberNavController()
        NavHost(
            navController = navController, 
            startDestination = "",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = "") {
                HomeScreen(pastGames = emptyList()) {
                    
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        }
    )

}