package ir.shervin.messenger.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material.icons.rounded.GroupAdd
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ir.shervin.messenger.R
import ir.shervin.messenger.components.DrawerItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(navController: NavHostController) {
	var activeGroup by remember { mutableIntStateOf(0) }
	val drawerState = rememberDrawerState(DrawerValue.Closed)
	val scope = rememberCoroutineScope()
	
	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			ModalDrawerSheet(
				modifier = Modifier
					.background(
						Brush.linearGradient(
							0f to Color(0xFFFF6F00),
							0.7f to if (isSystemInDarkTheme()) Color.White else Color.Black,
						)
					)
					.requiredWidth(275.dp),
				drawerContainerColor = Color.Transparent
			) {
				Image(
					painterResource(R.drawable.app_icon),
					null,
					modifier = Modifier
						.clip(CircleShape)
						.size(100.dp)
						.align(Alignment.CenterHorizontally)
				)
				Text(
					"Shervin Shahedi",
					modifier = Modifier
						.align(Alignment.CenterHorizontally)
						.padding(top = 8.dp)
				)
				
				HorizontalDivider(
					Modifier
						.padding(vertical = 16.dp, horizontal = 8.dp)
				)
				
				DrawerItem("Profile", Icons.Rounded.AccountCircle) {
					navController.navigate("account")
				}
				DrawerItem("New Group", Icons.Rounded.Groups) {}
				DrawerItem("Contacts", Icons.Rounded.Contacts) {}
				DrawerItem("Saved Messages", Icons.Rounded.Bookmark) {}
				DrawerItem("Settings", Icons.Rounded.Settings) {}
				DrawerItem("Invite Friends", Icons.Rounded.GroupAdd) {}
				DrawerItem("About", Icons.Rounded.Info) {}
			}
		}
	) {
		Scaffold(
			topBar = {
				TopAppBar(
					title = { Text("Shervin") },
					navigationIcon = {
						IconButton(onClick = {
							scope.launch {
								drawerState.open()
							}
						}) {
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
}
