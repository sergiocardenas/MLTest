package com.example.mercadolibretest.presentation.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mercadolibretest.R
import com.example.mercadolibretest.presentation.viewmodel.HomeViewModel

@Composable
fun HomeSearchScreen(
    viewModel: HomeViewModel
) {
    val homeState = viewModel.homeState.collectAsState()
    val searchQuery = remember { mutableStateOf(homeState.value.query) }
    val currentlySearch = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val onResult: (success: Boolean) -> Unit = {
        currentlySearch.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                 },
                label = {
                    Text(
                        text = "Buscar",
                        maxLines = 1
                    )
                },
                modifier = Modifier
                    .weight(
                        if(homeState.value.hasSearched)
                            if (isPortrait) 7.4f else 8.5f
                        else
                            if (isPortrait) 8.75f else 9.3f
                    )
                    .padding(end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .weight(
                        if(homeState.value.hasSearched)
                            if (isPortrait) 2.6f else 1.5f
                        else
                            if (isPortrait) 1.25f else 0.7f
                    ),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                if(homeState.value.hasSearched){
                    IconButton(
                        onClick = {
                            viewModel.resetHomeState()
                            searchQuery.value = ""
                        },
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                colorResource(id = R.color.mercado_yellow),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "clear",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(36.dp))
                }

                IconButton(
                    onClick = {
                        currentlySearch.value = true
                        viewModel.searchItem(
                            item = searchQuery.value,
                            onSearchSuccess = onResult,
                        )
                    },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                        .background(
                            colorResource(id = R.color.mercado_yellow),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(homeState.value.hasSearched){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

            }
        }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lg_mercadolibre),
                    contentDescription = "ML_Logo",
                    modifier = Modifier.size(120.dp),
                )
                Text(
                    text = "Busca productos, marcas y mas",
                    maxLines = 1,
                    fontSize = 20.sp,
                )
            }
        }
    }

    if(currentlySearch.value){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_grey)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            val strokeWidth = 5.dp
            CircularProgressIndicator(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            Color.Blue,
                            radius = size.width / 2 - strokeWidth.toPx() / 2,
                            style = Stroke(strokeWidth.toPx())
                        )
                    },
                color = Color.LightGray,
                strokeWidth = strokeWidth
            )
        }
    }
}