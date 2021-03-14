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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val sessions = MutableLiveData<List<Session>>()

    init {
        sessions.value = listOf(
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
    }

    fun getLiveSessions(): LiveData<List<Session>> {
        return sessions
    }
}
