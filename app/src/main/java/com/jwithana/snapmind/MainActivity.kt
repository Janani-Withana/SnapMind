package com.jwithana.snapmind

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jwithana.snapmind.ui.theme.SnapMindTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen(
                onAnimationComplete = {
                    startActivity(Intent(this, ChatActivity::class.java))
                    finish()
                }
            )
        }
    }

    @Composable
    fun SplashScreen(onAnimationComplete: () -> Unit) {
        var startAnimation by remember { mutableStateOf(false) }

        SetBarColor(color = Color.Black)
        LaunchedEffect(true) {
            startAnimation = true
            delay(3000) // Change duration as needed
            onAnimationComplete()
        }

        val infiniteTransition = rememberInfiniteTransition(label = "")

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            AnimatedLogo(scale = scale)
        }
    }

    @Composable
    fun AnimatedLogo(scale: Float) {
        Image(
            painter = painterResource(id = R.drawable.snapmind_logo), // Change with your animated logo
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .scale(scale),
            contentScale = ContentScale.Fit
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview() {
        SnapMindTheme {
            SplashScreen(onAnimationComplete = {})
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}
