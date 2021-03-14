package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun WelcomeScreen(
    onClickSignUp: () -> Unit,
    onClickSignIn: () -> Unit
) {
    Image(
        painter = painterResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_welcome else R.drawable.ic_light_welcome),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_logo else R.drawable.ic_light_logo),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onClickSignUp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.welcome_screen_sign_up)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onClickSignIn,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.welcome_screen_log_in)
            )
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    MyTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            WelcomeScreen(
                onClickSignUp = {},
                onClickSignIn = {}
            )
        }
    }
}