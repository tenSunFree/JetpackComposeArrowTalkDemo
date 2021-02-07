package com.example.jetpackcomposearrowtalkdemo.common.composables

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.wrapContentSize
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.example.jetpackcomposearrowtalkdemo.R

@Composable
fun VStack(child: @Composable() () -> Unit) =
    VerticalScroller {
        Column(modifier = Modifier.fillMaxWidth()) {
            child()
        }
    }

@Composable
fun LoadingView() =
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }

@Composable
fun ErrorView(message: String) =
    Column(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Image(
            asset = imageResource(id = R.mipmap.jetpack_hero_arrow),
            modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)
        )
        Text(
            text = message,
            modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center).padding(16.dp)
        )
    }

@Composable
fun Divider() =
    Divider(color = Color(0xFFF28A30))
