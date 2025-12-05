package com.example.tasty

data class Recipe(
    val id: String,
    val title: String,
    val ingredients: Array<Ingredient>,
    val method: Array<String>,
    val imageUrl: String
)
