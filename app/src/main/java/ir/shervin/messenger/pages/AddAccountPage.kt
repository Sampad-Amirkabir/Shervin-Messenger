package ir.shervin.messenger.pages

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountPage(navController: NavHostController) {
	val secondaryColor = if (isSystemInDarkTheme()) Color(0xFF468266) else Color(0xFFFFF176)
	var phoneNumber by remember { mutableStateOf("") }
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("Add Account")
				},
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Rounded.ArrowBack, null)
					}
				},
				colors = TopAppBarDefaults.topAppBarColors(containerColor = secondaryColor)
			)
		}
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
				.imePadding(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "Enter your phone number",
				fontSize = 24.sp,
				color = Color(0xFF667788)
			)
			
			Spacer(Modifier.height(20.dp))
			
			OutlinedTextField(
				value = phoneNumber,
				onValueChange = {
					if (it.contains("""^[0-9]*$""".toRegex())) {
						phoneNumber = it
					}
				},
				textStyle = LocalTextStyle.current.copy(
					textAlign = TextAlign.Center,
					fontSize = 20.sp
				),
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
			)
			
			Spacer(Modifier.height(20.dp))
			
			Button(onClick = { navController.navigate("verification") }) {
				Text(
					text = "Continue",
					fontSize = 20.sp
				)
			}
		}
	}
}
