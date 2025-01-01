package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    LemonadeApp()
                }
           // }
        }
    }
}
@Preview
@Composable
fun LemonadeApp() {
    ImageWithInstructions(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        //color = MaterialTheme.colorScheme.background
    )
}

@Composable
fun ImageWithInstructions(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(1) }
    var imageResource = R.drawable.lemon_tree
    var imageText = R.string.lemon_tree
    var instructions = R.string.tap_the_lemon_tree_to_select_a_lemon
    var moreclicks = 1
    when (counter % 4) {
        1 -> { imageResource = R.drawable.lemon_tree
               imageText = R.string.lemon_tree
               instructions = R.string.tap_the_lemon_tree_to_select_a_lemon
        }
        2 -> { imageResource = R.drawable.lemon_squeeze
               imageText = R.string.lemon
               instructions = R.string.keep_tapping_the_lemon_to_squeeze_it
               moreclicks = (1..3).random()
        }
        3 -> { imageResource = R.drawable.lemon_drink
               imageText = R.string.glass_of_lemonade
               instructions = R.string.tap_the_lemonade_to_drink_it
        }
        else -> { imageResource = R.drawable.lemon_restart
                  imageText = R.string.empty_glass
                  instructions = R.string.tap_the_empty_glass_to_start_again
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                if ((counter % 4 == 2) && (moreclicks != 0)){
                    moreclicks--
                }
                else {
                    counter++
                }
            }, shape = RoundedCornerShape(40.dp)
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageText)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(instructions),
            fontSize = 18.sp
        )
    }
}