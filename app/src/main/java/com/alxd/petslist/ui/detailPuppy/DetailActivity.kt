package com.alxd.petslist.ui.detailPuppy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alxd.petslist.data.Puppy
import com.alxd.petslist.ui.theme.PetsListTheme

class DetailActivity : ComponentActivity() {

    companion object {
        private const val PUPPY_ID = "puppy_id"
        fun newIntent(context: Context, puppy: Puppy) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(PUPPY_ID, puppy)
            }
    }

    private val puppy: Puppy by lazy {
        intent?.getSerializableExtra(PUPPY_ID) as Puppy
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PetsListTheme {
                DetailContent(puppy)
            }
        }
    }
}