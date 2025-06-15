package com.diajarkoding.florafauna.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.diajarkoding.florafauna.data.Species
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SpeciesCarousel(
    speciesList: List<Species>,
    onItemClick: (Species) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % speciesList.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
            count = speciesList.size,
            state = pagerState,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            val species = speciesList[page]
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = modifier
                    .fillMaxSize()
                    .clickable { onItemClick(species) }
            ) {
                AsyncImage(
                    model = species.imageUrl,
                    contentDescription = species.name,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }

    }
}
