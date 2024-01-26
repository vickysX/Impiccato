package com.example.impiccato.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.impiccato.R
import com.example.impiccato.ui.theme.ImpiccatoTheme

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUIState by gameViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(
            score = gameUIState.score,
            wrongGuesses = gameUIState.numWrongGuessesPerWord
        )
        GameLayout(
            isGuessedLetterWrong = gameUIState.isGuessedLetterWrong,
            currentWord = gameUIState.currentWordToGuess,
            letterGuessed = gameViewModel.userGuess,
            onGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() }
        )
        // Buttons here
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                enabled = gameUIState.isBulbButtonEnabled,
                onClick = { gameViewModel.requireHint() }
            ) {
                Icon(
                    imageVector = Icons.Filled.TipsAndUpdates,
                    contentDescription = stringResource(R.string.show_hint)
                )
            }
            Button(
                onClick = {
                    gameViewModel.goToNextWord(
                        gameUIState.score
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.next_word)
                )
            }
        }
        if (gameUIState.isHintRequired) {
            WordDefinition(
                wordDefinition = gameUIState.wordDefinition
            )
        }
        if (gameUIState.isGameOver) {
            FinalAlertDialog(
                isGameLost = true,
                score = gameUIState.score
            ) {
                gameViewModel.resetGame()
            }
        }
        if (gameUIState.isGameEnded) {
            FinalAlertDialog(
                isGameLost = false,
                score = gameUIState.score
            ) {
                gameViewModel.resetGame()
            }
        }
    }
}

@Composable
fun GameStatus(
    modifier: Modifier = Modifier,
    score: Int,
    wrongGuesses: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp)
    ) {
        Text(
            text = stringResource(R.string.score, score),
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.wrong_guesses, wrongGuesses),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    isGuessedLetterWrong: Boolean,
    currentWord: String,
    letterGuessed: String,
    onGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = currentWord,
            fontSize = 35.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = letterGuessed,
            onValueChange = onGuessChanged,
            modifier = Modifier.fillMaxWidth(),
            isError = isGuessedLetterWrong,
            label = {
                if (isGuessedLetterWrong) {
                    stringResource(R.string.wrong_letter)
                } else {
                    stringResource(R.string.insert_letter)
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )
    }
}

@Composable
fun WordDefinition(
    modifier: Modifier = Modifier,
    wordDefinition: String
) {
    Icon(
        imageVector = Icons.Filled.TipsAndUpdates,
        contentDescription = null,
        tint = Color.Yellow
    )
    Text(
        text = wordDefinition,
        fontSize = 16.sp
    )
    Text(
        text = stringResource(R.string.copyright_disclaimer),
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp
    )
}

@Composable
fun FinalAlertDialog(
    modifier: Modifier =
        Modifier
            .padding(24.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
    isGameLost: Boolean,
    score: Int,
    restartGame: () -> Unit
) {
    val activity = (LocalContext.current as Activity)
    val onDismissClick = { activity.finish() }
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        dismissButton = {
            TextButton(
                onClick = onDismissClick
            ) {
                Text(
                    text = stringResource(R.string.exit_game)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = restartGame
            ) {
                Text(
                    text = stringResource(R.string.reset_game)
                )
            }
        },
        title = {
            when {
                isGameLost -> Text(
                    stringResource(id = R.string.game_over)
                )
                else -> Text(
                    stringResource(id = R.string.congratulations)
                )
            }
        },
        text = {
            when {
                isGameLost -> Text(
                    stringResource(id = R.string.g_o_dialog)
                )
                else -> Text(
                    stringResource(id = R.string.dialog_text, score)
                )
            }
        }
    )
}

@Composable
@Preview(
    showSystemUi = true
)
fun LightThemePreview() {
    ImpiccatoTheme(darkTheme = false) {
        GameScreen()
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun GameOverDialogPreview() {
    ImpiccatoTheme() {
        FinalAlertDialog(isGameLost = true, score = 0) {

        }
    }
}