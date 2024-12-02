package com.example.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.navigation.ui.theme.NavigationTheme
import okhttp3.internal.wait

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationStack()
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Meal API-DataViewer")
        Button(onClick = {
            navController.navigate(route = Screen.Categories.route)
        }) {
            Text(text = "Explore the category")
        }
    }
}

@Composable
fun CategoriesScreen(navController: NavController) {
    val categoryVM = CategoryViewModel()
    categoryVM.updateCategories {}

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 12.dp
        ),
        content = {
            items(categoryVM.categories.count()) { index ->
                val category = categoryVM.categories[index]
                Card(
                    onClick = {
                        navController.navigate(Screen.Detail.route + "?id=${category.id}")
                    }
                ) {
                    Column {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = category.thumb,
                            contentDescription = ""
                        )
                        Text(text = category.name)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id: String?, navController: NavController) {
    if (id == null) {
        navController.navigate(Screen.Categories.route)
        return
    }

    var category = remember { mutableStateOf<Category?>(null) }
    val categoryVM = CategoryViewModel()
    categoryVM.updateCategories {
        Log.d("Main", "Categories loaded")
        category.value = categoryVM.categories.find { it.id == id }
        if (category.value == null) {
            navController.navigate(Screen.Categories.route)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = category.value?.name ?: "")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Categories.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Main.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                }
            )
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = category.value?.thumb ?: "",
                contentDescription = ""
            )
            Text(text = category.value?.description ?: "")
        }
    }
}