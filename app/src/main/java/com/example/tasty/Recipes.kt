package com.example.tasty

object STUB {
    private val categories: Array<Сategory> = arrayOf(
        Сategory(0, "Бургеры", "Рецепты всех популярных видов бургеров", "burger.png"),
        Сategory(1, "Десерты", "Самые вкусные рецепты десертов специально для вас", "dessert.png"),
        Сategory(2, "Пицца", "Пицца на любой вкус и цвет. Лучшая подборка для тебя", "pizza.png"),
        Сategory(3, "Рыба", "Печеная, жареная, сушеная, любая рыба на твой вкус", "fish.png"),
        Сategory(4, "Супы", "От классики до экзотики: мир в одной тарелке", "soup.png"),
        Сategory(5, "Салаты", "Хрустящий калейдоскоп под соусом вдохновения", "salad.png"),
    )

    fun getCategories(): Array<Сategory> {
        return categories
    }
}
