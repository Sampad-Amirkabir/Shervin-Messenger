package ir.shervin.messenger.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavHostController) {
	var activeGroup by remember { mutableIntStateOf(0) }
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Shervin") },
				navigationIcon = {
					IconButton(onClick = {}) {
						Icon(Icons.Rounded.Menu, null)
					}
				},
				actions = {
					IconButton(onClick = {}) {
						Icon(Icons.Rounded.Search, null, modifier = Modifier.scale(-1f, 1f))
					}
				}
			)
		}
	) { contentPadding ->
		Column(Modifier.padding(contentPadding)) {
			LazyRow(
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				items(3) {
					Column(
						modifier = Modifier.width(100.dp)
					) {
						Text(
							"Group $it",
							fontSize = 18.sp,
							modifier = Modifier
								.padding(horizontal = 16.dp)
								.clickable {
									activeGroup = it
								}
						)
						
						AnimatedVisibility(
							visible = activeGroup == it,
							enter = slideInHorizontally { it },
							exit = slideOutHorizontally { -it }
						) {
							HorizontalDivider(
								modifier = Modifier.fillMaxWidth(),
								thickness = 3.dp,
								color = MaterialTheme.colorScheme.primary
							)
						}
					}
				}
			}
		}
	}
}