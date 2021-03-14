package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val sessions = viewModel.getLiveSessions().observeAsState()
    HomeFullScreen(sessions = sessions.value!!)
}

@Composable
fun HomeFullScreen(sessions: List<Session>) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* nothing to do*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background
            ) {
                BottomNavigationItem(
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_spa),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(R.string.home_screen_bottom_bar_home),
                            style = MaterialTheme.typography.caption
                        )
                    },
                    selected = true,
                    onClick = { /* nothing to do */ }
                )
                BottomNavigationItem(
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    label = {
                        Text(
                            stringResource(R.string.home_screen_bottom_bar_profile),
                            style = MaterialTheme.typography.caption
                        )
                    },
                    selected = false,
                    onClick = { /* nothing to do */ }
                )
            }
        }
    ) {
        HomeContentScreen(sessions = sessions)
    }
}

@Composable
fun HomeContentScreen(sessions: List<Session>) {
    val (search, setSearch) = remember { mutableStateOf("") }

    Column {
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = search,
            onValueChange = { setSearch(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.log_in_screen_text_field_email),
                    style = MaterialTheme.typography.body1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                    modifier = Modifier.size(18.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.home_screen_favorite_title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        val favoriteSessions = sessions.filter { it.isFavorite }
        val sessionPairs = createSessionPairsList(favoriteSessions)
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = sessionPairs) { sessionPairs ->
                Column {
                    FavoriteCard(sessionPairs.first)
                    if (sessionPairs.second != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        FavoriteCard(sessionPairs.second!!)
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        val sessionsBody = sessions.filter { it.type == SessionType.BODY && !it.isFavorite }
        SessionTypeView(stringResource(id = R.string.home_screen_body_title), sessionsBody)

        Spacer(modifier = Modifier.height(24.dp))
        val sessionsMind = sessions.filter { it.type == SessionType.MIND && !it.isFavorite }
        SessionTypeView(stringResource(id = R.string.home_screen_mind_title), sessionsMind)
    }
}

@Composable
fun FavoriteCard(session: Session) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .width(192.dp)
            .background(
                MaterialTheme.colors.surface,
                MaterialTheme.shapes.small
            )
    ) {
        Image(
            painter = painterResource(id = session.cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = session.name,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun SessionTypeView(sessionTitle: String, sessions: List<Session>) {
    Text(
        text = sessionTitle,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = sessions) { session ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = session.cover),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(88.dp)
                        .width(88.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = session.name,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}

private fun createSessionPairsList(sessions: List<Session>): List<Pair<Session, Session?>> {
    val sessionPairs = mutableListOf<Pair<Session, Session?>>()
    for (i in 0 until sessions.size / 2) {
        sessionPairs.add(Pair(sessions[i * 2], sessions[i * 2 + 1]))
    }
    if (sessions.size % 2 != 0) {
        sessionPairs.add(Pair(sessions.last(), null))
    }
    return sessionPairs
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    MyTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val sessions = provideSessions()
            HomeFullScreen(sessions = sessions)
        }
    }
}

private fun provideSessions() = listOf(
    Session(
        "Short mantras",
        R.drawable.short_mantras,
        true,
        SessionType.BODY
    ),
    Session(
        "Nature meditations",
        R.drawable.nature_meditation,
        true,
        SessionType.BODY
    ),
    Session(
        "Stress and anxiety",
        R.drawable.stress_and_anxiety,
        true,
        SessionType.BODY
    ),
    Session(
        "Self-massage",
        R.drawable.self_massage,
        true,
        SessionType.BODY
    ),
    Session(
        "Overwhelmed",
        R.drawable.overwhelmed,
        true,
        SessionType.BODY
    ),
    Session(
        "Nightly wind down",
        R.drawable.nightly_wind_down,
        true,
        SessionType.BODY
    ),
    Session(
        "Inversions",
        R.drawable.inversions,
        false,
        SessionType.BODY
    ),
    Session(
        "Quick yoga",
        R.drawable.quick_yoga,
        false,
        SessionType.BODY
    ),
    Session(
        "Stretching",
        R.drawable.streching,
        false,
        SessionType.BODY
    ),
    Session(
        "Tabata",
        R.drawable.tabata,
        false,
        SessionType.BODY
    ),
    Session(
        "HIIT",
        R.drawable.hiit,
        false,
        SessionType.BODY
    ),
    Session(
        "Pre-natal yoga",
        R.drawable.pre_natal_yoga,
        false,
        SessionType.BODY
    ),
    Session(
        "Meditate",
        R.drawable.meditate,
        false,
        SessionType.MIND
    ),
    Session(
        "With kids",
        R.drawable.with_kids,
        false,
        SessionType.MIND
    ),
    Session(
        "Aromatherapy",
        R.drawable.aromatherapy,
        false,
        SessionType.MIND
    ),
    Session(
        "On the go",
        R.drawable.on_the_go,
        false,
        SessionType.MIND
    ),
    Session(
        "With pets",
        R.drawable.with_pets,
        false,
        SessionType.MIND
    ),
    Session(
        "High stress",
        R.drawable.high_stress,
        false,
        SessionType.MIND
    )
)