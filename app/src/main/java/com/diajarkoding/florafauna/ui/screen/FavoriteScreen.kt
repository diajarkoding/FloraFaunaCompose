package com.diajarkoding.florafauna.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.diajarkoding.florafauna.R
import com.diajarkoding.florafauna.ui.components.SpeciesList
import com.diajarkoding.florafauna.viewmodel.SpeciesViewModel

@Composable
fun FavoriteScreen(
    viewModel: SpeciesViewModel,
    onItemClick: (speciesId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val favorites = viewModel.favorites

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.spesies_langka_favorit),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (favorites.isEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.belum_ada_favorit), style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites) { species ->
                    SpeciesList(species = species, onClick = {onItemClick(species.id)})
                }
            }
        }
    }



}