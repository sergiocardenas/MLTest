package com.example.mercadolibretest.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mercadolibretest.R
import com.example.mercadolibretest.presentation.viewmodel.DetailViewModel

@Composable
fun DetailMLItemScreen (
    viewModel: DetailViewModel
) {
    val item = viewModel.item.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            var textCondition = item.value.condition
            if(item.value.sold_quantity>0){
                textCondition+=" | "+item.value.sold_quantity.toString()+" sold"
            }
            Text(
                text = textCondition,
                maxLines = 1,
                fontSize = 14.sp,
                color= Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 8.dp,
                        bottom = 4.dp
                    )
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = item.value.title,
                maxLines = 2,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.value.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .weight(5f)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.value.currency_id +" "+item.value.price.toString(),
                    maxLines = 1,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if(item.value.original_price>0){
                    Text(
                        text = item.value.currency_id+" "+item.value.original_price.toString(),
                        maxLines = 1,
                        fontSize = 14.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        ),
                        color= Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 8.dp,
                                bottom = 4.dp
                            )
                    )
                }else{
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
        Text(
            text = "Tags",
            maxLines = 1,
            fontSize = 14.sp,
            color= Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        if(item.value.tags.isNotEmpty()){
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ){
                items(item.value.tags) { tag ->
                    Column(
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                            .background(
                                colorResource(id = R.color.mercado_yellow),
                                shape = CircleShape
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tag,
                            maxLines = 1,
                            fontSize = 14.sp,
                            color= Color.Black,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }
                }

            }
        }
    }
}