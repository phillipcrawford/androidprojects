package com.example.helloworldapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldAppTheme {
                HelloWorldText()
            }
        }
    }
}

@Composable
fun HelloWorldText() {
    Text(text = "Hello, World!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloWorldAppTheme {
        HelloWorldText()
    }
}