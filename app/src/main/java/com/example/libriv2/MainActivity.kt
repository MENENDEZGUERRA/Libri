package com.example.libriv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.libriv2.ui.Navigation.AppNavigation
import com.example.libriv2.ui.theme.LibriV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibriV2Theme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Applicacion() {
    LibriV2Theme {
        AppNavigation()
    }
}
