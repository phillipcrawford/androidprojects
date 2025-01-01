package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GenerateArticle()
                }
            }
        }
    }
}

@Composable
fun GenerateArticle() {
    SpecificArticle(
        title = stringResource(R.string.title),
        summary = stringResource(R.string.summary),
        explanation = stringResource(R.string.explanation),
        imagePainter  = painterResource(R.drawable.bg_compose_background)
    )
}

@Composable
fun SpecificArticle(
    title: String,
    summary: String,
    explanation: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            //contentScale = ContentScale.Crop,
        )
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = summary,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = explanation,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
        modifier.align(alignment = Alignment.CenterHorizontally)

    }
}

@Preview(showBackground = true, name = "tilso")
@Composable
fun GreetingPreview() {
    ComposeArticleTheme {
        GenerateArticle()
    }
}