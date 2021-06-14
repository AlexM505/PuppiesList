package com.alxd.petslist.ui.listPuppy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.alxd.petslist.data.DataProvider
import com.alxd.petslist.data.Puppy
import com.alxd.petslist.ui.detailPuppy.DetailActivity
import com.alxd.petslist.ui.theme.PetsListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsListTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp {
                        startActivity(DetailActivity.newIntent(this,it))
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToDetail: (Puppy) -> Unit){
    Scaffold(
        content = { PetsHomeContent(navigateToDetail = navigateToDetail) }
    )
}

@Composable
fun PetsHomeContent(navigateToDetail: (Puppy) -> Unit) {
    val puppies = remember { DataProvider.puppyList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(puppies){item ->
            PuppyListItem(puppy = item, navigateToDetail)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PetsListTheme {
//        MyApp()
//    }
//}