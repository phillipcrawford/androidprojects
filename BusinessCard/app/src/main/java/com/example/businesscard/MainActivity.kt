package com.example.businesscard

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ){
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(){
    val backgroundColor = Color(0xFFd2e8d4)
    Column (
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
    ) {
        Headline()
        ContactInfo()
    }
}

@Composable
fun Headline(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.android_logo)
    val backgroundImageColor = Color(0xFF073042)
    Column(
        modifier = Modifier
             .padding(top = 260.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Column (Modifier.weight(1f)) {}

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .background(backgroundImageColor)
                    .weight(1f)
                    .padding()

            )

            Column (Modifier.weight(1f)) {}
        }

        Text(
            text = "Phillip Crawford ",
            modifier = modifier.padding(8.dp),
            fontSize = 40.sp
        )
        Text(
            text = "Full Stack Web Developer ",
            modifier = modifier.padding(8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF026b38)
        )
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .padding(60.dp,0.dp, 0.dp, 60.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Row  {
            //val MyAppIcons = Icons.Rounded
            //SomeComposable(icon = MyAppIcons.Menu)
            Icon(
                Icons.Rounded.Phone,
                tint = Color(0xFF026b38),
                contentDescription = "Phone",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = " (805) 689 5688",
                fontSize = 20.sp
            )
        }
        Row {
            Icon(
                Icons.Rounded.Share,
                tint = Color(0xFF026b38),
                contentDescription = "Share",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = " @philswalipcr86",
                fontSize = 20.sp
            )
        }
        Row {
            Icon(
                Icons.Rounded.Email,
                tint = Color(0xFF026b38),
                contentDescription = "Email",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = " phillip.crawford3@gmail.com",
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}