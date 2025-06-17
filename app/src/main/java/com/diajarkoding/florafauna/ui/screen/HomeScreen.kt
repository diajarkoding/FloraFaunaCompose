package com.diajarkoding.florafauna.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.diajarkoding.florafauna.R
import com.diajarkoding.florafauna.data.Species
import com.diajarkoding.florafauna.ui.components.SpeciesCarousel
import com.diajarkoding.florafauna.ui.components.SpeciesGridItem
import com.diajarkoding.florafauna.ui.components.SpeciesList

@Composable
fun HomeScreen(
    speciesList: List<Species>,
    onItemClick: (Species) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                text = stringResource(R.string.spesies_langka_dunia),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item(span = { GridItemSpan(2) }) {
            SpeciesCarousel(
                speciesList = speciesList.take(20),
                onItemClick = onItemClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        items(speciesList, key = { it.id }) { species ->
            SpeciesGridItem(species = species, onClick = onItemClick)
        }
    }
}

