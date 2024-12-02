package com.example.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = mutableStateListOf<Category>()
    val categories: List<Category> = _categories

    private val api = RetrofitInstance.api

    fun updateCategories(onCategoriesLoaded: () -> Unit) {
        viewModelScope.launch {
            _categories.addAll(api.getCategories().categories)
            onCategoriesLoaded()
        }
    }
}