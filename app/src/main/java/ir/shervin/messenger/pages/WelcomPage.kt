package ir.shervin.messenger.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WelcomePage(navController: NavController) {
	val titles = listOf(
		"Welcome to Shervin Messenger",
		"The Best Messaging App in the World",
		"Bypass every sanction",
		"Video Call",
		"Sponsored by The Big Shervin"
	)
	var titleIndex by remember { mutableIntStateOf(0) }
	
	BackHandler(titleIndex >= 0) {
		titleIndex--
	}
	
	Scaffold { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxHeight()
		) {
			Spacer(Modifier.weight(1f))
			
			AnimatedContent(
				targetState = titleIndex,
				transitionSpec = {
					if (targetState > initialState) {
						slideInVertically { it } + fadeIn() togetherWith
							slideOutVertically { -it } + fadeOut()
					} else {
						slideInVertically { -it } + fadeIn() togetherWith
							slideOutVertically { it } + fadeOut()
					}
				}
			) {
				Text(
					titles[it],
					fontSize = 24.sp,
					modifier = Modifier.fillMaxWidth(),
					textAlign = TextAlign.Center
				)
			}
			
			Spacer(Modifier.weight(1f))
			
			Button(
				onClick = {
					if (titleIndex == titles.lastIndex) {
						navController.navigate("phone-number")
					} else {
						titleIndex++
					}
				},
				modifier = Modifier.align(Alignment.CenterHorizontally)
			) {
				Text(if (titleIndex == titles.lastIndex) "Start Messaging" else "Next")
			}
		}
	}
}
