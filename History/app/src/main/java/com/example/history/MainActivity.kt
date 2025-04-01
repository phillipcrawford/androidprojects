package com.example.history

import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.history.ui.theme.HistoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TwoBySixteen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TwoBySixteen(name: String, modifier: Modifier = Modifier) {
    Column {
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(40.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Vegetarian",
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = "Pescetarian",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Pescatarian",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Keto",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Organic",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "GMO-Free",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Hello $name!",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HistoryTheme {
        TwoBySixteen("Android")
    }
}