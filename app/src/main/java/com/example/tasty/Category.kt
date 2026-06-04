package com.example.tasty

data class Category(
    val id: Int,
    val title: String,
    val description: String,
    val imageURL: String
)


// bundle constants
const val ARG_CATEGORY_ID = "category_id"
const val ARG_CATEGORY_NAME = "category_name"
const val ARG_CATEGORY_IMAGE_URL = "category_image_url"