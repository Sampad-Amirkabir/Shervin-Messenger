package ir.shervin.messenger.pages

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountPage(navController: NavHostController) {
	var name by remember { mutableStateOf("") }
	var lname by remember { mutableStateOf("") }
	var bio by remember { mutableStateOf("") }
	var id by remember { mutableStateOf("") }
	val secondColor = if (isSystemInDarkTheme()) Color(0xFF00695C) else Color(0xFF4DB6AC)
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Edit") },
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				},
				actions = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.Rounded.TaskAlt, null)
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(containerColor = secondColor)
			)
		},
	) { contentPadding ->
		Column(
			Modifier
				.padding(contentPadding)
				.padding(start = 20.dp)
		) {
			Text(
				text = "Your Name",
				fontSize = 18.sp,
				modifier = Modifier.padding(top = 15.dp)
			)
			Spacer(Modifier.height(10.dp))
			OutlinedTextField(
				value = name,
				onValueChange = { name = it }
			)
			Spacer(Modifier.height(20.dp))
			
			Text(
				text = "Your Last Name",
				fontSize = 18.sp
			)
			Spacer(Modifier.height(10.dp))
			OutlinedTextField(
				value = lname,
				onValueChange = { lname = it }
			)
			Spacer(Modifier.height(20.dp))
			
			Text(
				text = "Biography",
				fontSize = 18.sp
			)
			Spacer(Modifier.height(10.dp))
			OutlinedTextField(
				value = bio,
				onValueChange = { bio = it }
			)
			Spacer(Modifier.height(20.dp))
			
			Text(
				text = "ID",
				fontSize = 18.sp
			)
			Spacer(Modifier.height(10.dp))
			OutlinedTextField(
				value = id,
				onValueChange = {
					if (it.contains("""^[a-zA-Z0-9]*$""".toRegex())) {
						id = it
					}
				}
			)
		}
	}
}
