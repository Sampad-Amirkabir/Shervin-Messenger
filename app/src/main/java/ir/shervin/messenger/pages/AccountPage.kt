package ir.shervin.messenger.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ir.shervin.messenger.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPage(navController: NavHostController) {
	val premiumColor = if (isSystemInDarkTheme()) Color(0xFF00695C) else Color(0xFF4DB6AC)
	
	Scaffold(
		topBar = {
			Column(
				modifier = Modifier.background(premiumColor)
			) {
				TopAppBar(
					colors = TopAppBarDefaults.topAppBarColors(containerColor = premiumColor),
					title = { Text("Accounts") },
					navigationIcon = {
						IconButton(onClick = { navController.popBackStack() }) {
							Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
						}
					},
					actions = {
						IconButton(onClick = {}) {
							Icon(Icons.Rounded.Edit, null)
						}
						IconButton(onClick = {}) {
							Icon(Icons.Rounded.Add, null)
						}
					}
				)
				
				Row(
					modifier = Modifier.padding(start = 56.dp, top = 16.dp, bottom = 16.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Image(
						painterResource(R.drawable.app_icon),
						null,
						modifier = Modifier
							.clip(CircleShape)
							.size(85.dp)
					)
					
					Column(
						modifier = Modifier.padding(start = 16.dp)
					) {
						Text("Shervin", fontSize = 25.sp)
					}
					
					Spacer(Modifier.weight(1f))
					
					IconButton(onClick = {}) {
						Icon(
							Icons.Rounded.ArrowBackIosNew,
							null,
							modifier = Modifier.rotate(-90f)
						)
					}
				}
			}
		}
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.background(premiumColor.copy(alpha = 0.3f))
					.padding(horizontal = 40.dp, vertical = 16.dp)
					.fillMaxWidth()
			) {
				Icon(Icons.Rounded.AccountCircle, null)
				Spacer(Modifier.width(16.dp))
				Text("Jane", fontSize = 20.sp)
			}
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.background(premiumColor.copy(alpha = 0.3f))
					.padding(horizontal = 40.dp, vertical = 16.dp)
					.fillMaxWidth()
			) {
				Icon(Icons.Rounded.AccountCircle, null)
				Spacer(Modifier.width(16.dp))
				Text("Roya", fontSize = 20.sp)
			}
			
			Text(
				"Info",
				fontSize = 20.sp,
				modifier = Modifier.padding(start = 40.dp, top = 16.dp),
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.primary
			)
			
			Text(
				text = "Phone Number",
				modifier = Modifier.padding(start = 56.dp, top = 10.dp),
				color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
			)
			Text(
				text = "09150058747",
				modifier = Modifier.padding(start = 56.dp),
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold
			)
			
			Text(
				text = "Biography",
				modifier = Modifier.padding(start = 56.dp, top = 10.dp),
				color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
			)
			Text(
				text = "Co-Founder of Shervin Co. Inc. Ltd.",
				modifier = Modifier.padding(start = 56.dp),
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold
			)
			
			Text(
				text = "ID",
				modifier = Modifier.padding(start = 56.dp, top = 10.dp),
				color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
			)
			Text(
				text = "@Shervin69",
				modifier = Modifier.padding(start = 56.dp),
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold
			)
			
			Text(
				text = "Date of Birth",
				modifier = Modifier.padding(start = 56.dp, top = 10.dp),
				color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
			)
			Text(
				text = "1969 January, 8th",
				modifier = Modifier.padding(start = 56.dp),
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold
			)
			
			HorizontalDivider(
				modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
			)
		}
	}
}