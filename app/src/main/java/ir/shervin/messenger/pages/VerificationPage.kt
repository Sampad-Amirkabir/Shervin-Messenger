package ir.shervin.messenger.pages

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VerificationPage(navController: NavHostController) {
	val codes = remember { mutableStateListOf<Int?>(null, null, null, null, null) }
	val focusRequesters = remember { List(5) { FocusRequester() } }
	val focusManager = LocalFocusManager.current
	var isLoading by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	
	Scaffold { contentPadding ->
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
		) {
			Row(
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				modifier = Modifier.fillMaxWidth()
			) {
				Spacer(Modifier.weight(1f))
				repeat(5) { index ->
					OutlinedTextField(
						enabled = !isLoading,
						value = codes[index]?.toString() ?: "",
						onValueChange = {
							codes[index] = it.last().digitToInt()
							
							if (index == focusRequesters.lastIndex) {
								focusManager.clearFocus()
								isLoading = true
								
								scope.launch {
									delay(1000)
									Handler(Looper.getMainLooper()).post {
										navController.navigate("main")
									}
								}
							} else {
								focusRequesters[index + 1].requestFocus()
							}
						},
						modifier = Modifier
							.size(52.dp)
							.focusRequester(focusRequesters[index]),
						textStyle = LocalTextStyle.current.copy(
							textAlign = TextAlign.Center,
							fontSize = 20.sp
						)
					)
				}
				Spacer(Modifier.weight(1f))
			}
			
			if (isLoading) {
				Spacer(Modifier.height(24.dp))
				CircularProgressIndicator()
			}
		}
	}
}
