package com.example.tasty

data class Recipe(
    val id: String,
    val title: String,
    val ingredients: List<Ingredient>,
    val method: List<String>,
    val imageUrl: String
)
