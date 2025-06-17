package com.diajarkoding.florafauna.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.diajarkoding.florafauna.R
import com.diajarkoding.florafauna.data.Species
import com.diajarkoding.florafauna.ui.components.SpeciesList
import com.diajarkoding.florafauna.viewmodel.SpeciesViewModel

@Composable
fun SearchScreen(
    viewModel: SpeciesViewModel,
    onItemClick: (Species) -> Unit,
) {
    val searchQuery by viewModel.searchQuery
    val results = viewModel.filteredSpecies
    val isSearchActive = viewModel.isSearchActive

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            placeholder = { Text(stringResource(R.string.cari_flora_atau_fauna)) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = stringResource(R.string.cari))
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChanged("") }) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.bersihkan))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        if (!isSearchActive) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.belum_ada_hasil_pencarian),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else if (results.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.tidak_ditemukan_hasil),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(results) { species ->
                    SpeciesList(species = species, onClick = { onItemClick(species) })
                }
            }
        }

    }
}
