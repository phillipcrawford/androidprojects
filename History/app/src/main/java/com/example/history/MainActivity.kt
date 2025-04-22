package com.example.history

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.compose.material3.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.history.ui.theme.BlackOne
import com.example.history.ui.theme.BlackTwo
import com.example.history.ui.theme.DOrange
import com.example.history.ui.theme.DTeal
import com.example.history.ui.theme.HistoryTheme
import com.example.history.ui.theme.LRed
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DietprefsStartPage(
                        name = "Android!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }
}

//@Composable
//fun DietprefsStartPage (name: String, modifier: Modifier = Modifier) {
//    Column (
//        modifier = modifier
//    ) {
//        //Header(name = "Preferences", modifier = modifier)
//        TwoBySixteen(name = name, modifier = modifier)
//    }
//}

@Composable
fun DietprefsStartPage(name: String, modifier: Modifier = Modifier) {
    Surface(color= BlackTwo) {
        Column {
            Row(
                Modifier.weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(5f)
                        .padding(4.dp, 4.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "Preferences",
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp,
                        color = LRed,
                        lineHeight = 80.sp
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 4.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "L",
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp,
                        lineHeight = 80.sp

                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "Pescatarian",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp)
                        .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                    .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                    .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
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
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
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
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Red Meat",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No MSG",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Sesame",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Milk",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Eggs",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Fish",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Shellfish",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Peanuts",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Tree Nuts",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "Gluten-Free",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DTeal)
                ) {
                    Text(
                        text = "No Soy",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(Modifier.weight(1f)) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "Low Price",
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "Second User",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(
                Modifier.weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(5f)
                        .padding(4.dp, 0.dp, 2.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BlackOne)
                ) {
                    Text(
                        text = "Search",
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp,
                        color = LRed,
                        lineHeight = 80.sp
                    )
                }
                Button(
                    onClick = {

                    }, shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                        .padding(2.dp, 0.dp, 4.dp, 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DOrange)
                ) {
                    Text(
                        text = "C",
                        modifier = Modifier.weight(1f),
                        fontSize = 24.sp,
                        lineHeight = 80.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HistoryTheme {
        DietprefsStartPage("Android")
    }
}