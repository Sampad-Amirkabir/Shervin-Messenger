package ir.shervin.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.shervin.messenger.pages.AccountPage
import ir.shervin.messenger.pages.AddAccountPage
import ir.shervin.messenger.pages.EditAccountPage
import ir.shervin.messenger.pages.MainPage
import ir.shervin.messenger.pages.PhoneNumberPage
import ir.shervin.messenger.pages.VerificationPage
import ir.shervin.messenger.pages.WelcomePage
import ir.shervin.messenger.ui.theme.ShervinTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ShervinTheme {
				MainComponent()
			}
		}
	}
}

@Composable
private fun MainComponent() {
	val navController = rememberNavController()
	
	NavHost(navController, "add-account") {
		composable("welcome") {
			WelcomePage(navController)
		}
		
		composable("phone-number") {
			PhoneNumberPage(navController)
		}
		
		composable("verification") {
			VerificationPage(navController)
		}
		
		composable("main") {
			MainPage(navController)
		}
		
		composable("account") {
			AccountPage(navController)
		}
		
		composable("edit-account") {
			EditAccountPage(navController)
		}
		
		composable("add-account") {
			AddAccountPage(navController)
		}
	}
}
