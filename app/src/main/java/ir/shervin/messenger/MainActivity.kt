package ir.shervin.messenger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.shervin.messenger.ui.theme.ShervinTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
	val titles = listOf(
		"Welcome to Shervin Messenger",
		"The Best Messaging App in the World",
		"Bypass every sanction",
		"Video Call",
		"Sponsored by The Big Shervin"
	)
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			var titleIndex by remember { mutableIntStateOf(0) }
			
			ShervinTheme {
				Scaffold {
					Column(
						modifier = Modifier
							.padding(it)
							.fillMaxHeight()
					) {
						Spacer(Modifier.weight(1f))
						
						Text(
							"Welcome To Shervin Messenger",
							fontSize = 24.sp,
							modifier = Modifier.fillMaxWidth(),
							textAlign = TextAlign.Center
						)
						
						Spacer(Modifier.weight(1f))
						
						Button(
							onClick = {
								Toast.makeText(this@MainActivity, "Broken, Not working", Toast.LENGTH_SHORT).show()
							},
							modifier = Modifier.align(Alignment.CenterHorizontally)
						) {
							Text("Start Messaging")
						}
					}
				}
			}
		}
	}
}
