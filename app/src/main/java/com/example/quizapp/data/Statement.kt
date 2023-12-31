package com.example.quizapp.data

import com.squareup.moshi.Json

data class Statement(
        @Json(name = "statement")
        val statement: String,
        val isCorrect: Boolean,
        val correctStatement: String
)