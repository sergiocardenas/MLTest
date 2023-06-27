package com.example.mercadolibretest.presentation.screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mercadolibretest.R
import com.example.mercadolibretest.presentation.viewmodel.HomeViewModel

@Composable
fun HomeSearchScreen(
    viewModel: HomeViewModel
) {
    val searchQuery = remember { mutableStateOf(viewModel.homeState.value.query) }
    val hasSearched = remember { mutableStateOf(viewModel.homeState.value.hasSearched) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
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
                    .weight(8.5f)
                    .padding(end = 4.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = { viewModel.searchItem(searchQuery.value) },
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            colorResource(id = R.color.mercado_yellow),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if(hasSearched.value){

        }else{
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lg_mercadolibre),
                    contentDescription = "ML_Logo",
                    modifier = Modifier.size(80.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Busca productos, marcas y mas",
                    maxLines = 1
                )
            }
        }

    }

}