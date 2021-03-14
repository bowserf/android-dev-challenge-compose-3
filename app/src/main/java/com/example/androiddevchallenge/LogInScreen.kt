/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LogInScreen(
    onClickLogIn: (String, String) -> Unit
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Image(
        painter = painterResource(if (isSystemInDarkTheme()) R.drawable.ic_dark_login else R.drawable.ic_light_login),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.log_in_screen_title),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { setEmail(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.log_in_screen_text_field_email),
                    style = MaterialTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { setPassword(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.log_in_screen_text_field_password),
                    style = MaterialTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onClickLogIn(email, password)
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.welcome_screen_button_log_in)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = stringResource(id = R.string.log_in_screen_no_account),
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.log_in_screen_sign_up),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun LogInScreenPreview() {
    MyTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            LogInScreen(
                onClickLogIn = { _, _ -> }
            )
        }
    }
}
