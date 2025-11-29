package com.example.tasty

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tasty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        // 1. Включить edge-to-edge (обратная совместимость)
        enableEdgeToEdge()

        setContentView(binding.root)

        // 2. Обработать системные отступы
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Получить FragmentManager
        val fragmentManager: FragmentManager = supportFragmentManager

        // 2. Создать экземпляр транзакции
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // 3. Добавить фрагмент в контейнер
        transaction.add(R.id.mainContainer, CategoriesListFragment())

        // 4. Зафиксировать транзакцию
        transaction.commit()
    }
}