package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var imageResource = R.drawable.cat1
    var artworkTitle = "Billy the Extravagant"
    var artworkArtist = "Janice (2008)"
    val dataEntriesCount = 2
    var counter by remember { mutableStateOf(1) }
    when (counter % dataEntriesCount) {
        1 -> {
            imageResource = R.drawable.cat1
            artworkTitle = "Billy the Resplendent"
            artworkArtist = "Janice (2008)"
        }
        else -> {
            imageResource = R.drawable.cat2
            artworkTitle = "Tommy the Magnificent"
            artworkArtist = "Sara (2015)"
        }
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "cat1",
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(2f)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(bottom = 24.dp, top = 48.dp)
                //.fillMaxWidth()
                .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(){
                Text(
                    text = artworkTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = artworkArtist
                )
            }
            Row (modifier = Modifier.fillMaxWidth()){
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { counter--}
                ) {
                    Text(
                        text = "Back",
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    shape = RoundedCornerShape(10.dp),
                    onClick = { counter++ }
                ) {
                    Text(
                        text = "Next",

                    )
                }
            }
        }
    }
}

@Composable
private fun NavButtons(
    modifier: Modifier = Modifier,
    name: String,
    counter: Int
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (name == "back"){
                   // counter--
                }
            }, shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = name)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArtSpaceLayoutPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}