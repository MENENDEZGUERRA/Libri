package com.example.libriv2.ui.Screens.ProfileScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.libriv2.R
import com.example.libriv2.ui.Navigation.AppNavigation
import com.example.libriv2.ui.theme.LibriV2Theme

class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibriV2Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun profile(navController: NavController) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(310.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lightorange),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Botón para retroceder
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, // Alinear contenido en el eje horizontal
                    verticalArrangement = Arrangement.Center // Alinear contenido en el eje vertical
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black, shape = CircleShape)
                            .size(200.dp)
                    )
                    Text(
                        text = "Username",
                        modifier = Modifier.padding(top = 15.dp),
                        color = Color.White,
                        fontSize = 35.sp, fontStyle = FontStyle.Italic
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp),
            ){
                Column {
                    Text(
                        text = "Description",
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.White,
                        fontSize = 45.sp, fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Username description",
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.White,
                        fontSize = 16.sp, fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Favourite Book",
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.White,
                        fontSize = 45.sp, fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Username's favourite book",
                        modifier = Modifier.padding(top = 5.dp),
                        color = Color.White,
                        fontSize = 16.sp, fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Principal Preview")
fun ProfilePreview() {
    LibriV2Theme {
        profile(navController = rememberNavController())
    }
}