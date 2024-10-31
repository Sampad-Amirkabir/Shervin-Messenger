package ir.shervin.messenger.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
fun PhoneNumberPage(navController: NavHostController) {
	var phoneNumber by remember { mutableStateOf("") }
	
	Scaffold { contentPadding ->
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.padding(contentPadding)
				.imePadding()
				.fillMaxSize()
		) {
			Text(
				"Enter you phone number",
				fontSize = 24.sp,
				color = Color(0xFF667788)
			)
			
			Spacer(Modifier.weight(1f))
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
			
			Spacer(Modifier.height(16.dp))
			
			Button(onClick = {
				navController.navigate("verification")
			}) {
				Text("Continue")
			}
			
			Spacer(Modifier.weight(1f))
		}
	}
}
