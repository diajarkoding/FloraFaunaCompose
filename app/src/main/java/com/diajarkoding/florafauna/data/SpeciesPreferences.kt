import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "favorites")

class SpeciesPreferences(private val context: Context) {
    private val favoriteKey = stringSetPreferencesKey(name = "favorite_species")

    val favoriteIds: Flow<Set<String>> = context.dataStore.data
        .map { preferences -> preferences[favoriteKey] ?: emptySet() }

    suspend fun toggleFavorite(id: String){
        context.dataStore.edit { preferences ->
            val current = preferences[favoriteKey]?.toMutableSet() ?: mutableSetOf()
            if (!current.add(id)) current.remove(id)
            preferences[favoriteKey] = current
        }
    }
}