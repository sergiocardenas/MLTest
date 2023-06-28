package com.example.mercadolibretest.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mercadolibretest.presentation.state.MLItemState


@Composable
fun ProductImage(
    url: String
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
    )
}

@Composable
fun SearchItem (
    item: MLItemState,
    onItemClick: (MLItemState) -> Unit
) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(120.dp)
            .clickable  {
                  onItemClick(item)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                
                ProductImage(url = item.thumbnail)
                
                Spacer(modifier = Modifier.width(16.dp))


                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {

                    Text(
                        text = item.title,
                        maxLines = 1,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp, bottom = 4.dp)
                    )

                    if(item.original_price>0){
                        Text(
                            text = item.currency_id+" "+item.original_price.toString(),
                            maxLines = 1,
                            fontSize = 12.sp,
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
                    Text(
                        text = item.currency_id +" "+item.price.toString(),
                        maxLines = 1,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    )
                }
            }
        }
    }
}