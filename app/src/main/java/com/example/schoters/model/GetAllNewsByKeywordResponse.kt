package com.example.schoters.model

data class GetAllNewsByKeywordResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)