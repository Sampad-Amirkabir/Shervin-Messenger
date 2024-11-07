package ir.shervin.messenger.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.shervin.messenger.ui.theme.ShervinTheme

@Composable
fun DrawerItem(
	text: String,
	icon: ImageVector,
	onClick: () -> Unit
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.clickable(onClick = onClick)
			.padding(vertical = 12.dp)
			.fillMaxWidth()
	) {
		Spacer(Modifier.width(16.dp))
		Icon(icon, null)
		Spacer(Modifier.width(20.dp))
		Text(text)
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun LightPreview() {
	ShervinTheme(darkTheme = false) {
		DrawerItem("HELLO", Icons.Rounded.Add){}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun DarkPreview() {
	ShervinTheme(darkTheme = true) {
		CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onBackground) {
			DrawerItem("HELLO", Icons.Rounded.Add){}
		}
	}
}
