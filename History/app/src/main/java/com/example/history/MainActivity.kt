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

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Vegetarian",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Pescetarian",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Vegan",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Keto",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Organic",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "GMO-Free",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Locally Sourced",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Raw",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Entree",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Sweet",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Kosher",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Halal",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Beef",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Chicken",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Bacon/Pork/Ham",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Seafood",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Low Sugar",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "High Protein",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Low Carb",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "No Alliums",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "No Pork Products",
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                onClick = {

                }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "No Alliums",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "No Pork Products",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Red Meat",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "No MSG",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Sesame",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "No Milk",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Eggs",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "No Fish",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Shellfish",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "No Peanuts",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Tree Nuts",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Gluten-Free",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "No Soy",
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Text(
                text = "Low Price",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Second User",
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