package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class Session(
    val name: String,
    @DrawableRes val cover: Int,
    val isFavorite: Boolean,
    val type: SessionType
)

enum class SessionType {
    MIND, BODY
}